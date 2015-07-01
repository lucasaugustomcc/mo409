package br.unicamp.ic.mo409.controller;

import java.text.SimpleDateFormat;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.unicamp.ic.mo409.dao.ChamadaDAO;
import br.unicamp.ic.mo409.dao.PresencaDAO;
import br.unicamp.ic.mo409.dao.TickDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.ChamadaState;
import br.unicamp.ic.mo409.model.Presenca;
import br.unicamp.ic.mo409.model.Tick;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.model.Usuario;

@Component
@RestController
@Transactional
public class AlunoController
{

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private ChamadaDAO chamadaDAO;

	@Autowired
	private PresencaDAO presencaDAO;

	@Autowired
	private TickDAO tickDAO;

	@ModelAttribute("usuario")
	public Usuario getUsuario()
	{

		Authentication auth = (Authentication) SecurityContextHolder
				.getContext().getAuthentication();

		UserDetails user = (UserDetails) auth.getPrincipal();
		Usuario usuario = usuarioDAO.loadUsuarioByUsername(user.getUsername());

		return usuario;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aluno/chamada/turmas", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_ALUNO" })
	@ResponseBody
	public JSONObject alunoChamada(@ModelAttribute("usuario") Usuario usuario)
	{
		Aluno aluno = usuario.getAluno();
		Chamada chamada;
		try
		{
			chamada = chamadaDAO.findChamadaAbertaAluno(aluno.getRaAluno());
		} catch (NoResultException e)
		{
			JSONObject obj = new JSONObject();
			return obj;
		}

		// construir resposta JSON
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");

		JSONObject obj = new JSONObject();
		obj.put("idChamada", chamada.getIdChamada());
		obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
		obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
		obj.put("professorChamada", chamada.getProfessor().getUsuario()
				.getNome());

		Turma turma = chamada.getTurma();
		JSONObject objTurma = new JSONObject();
		objTurma.put("idTurma", turma.getIdTurma());
		objTurma.put("codTurma", turma.getCodTurma());
		objTurma.put("codDisciplina", turma.getDisciplina().getCodDisciplina());
		objTurma.put("nomeDisciplina", turma.getDisciplina()
				.getNomeDisciplina());

		obj.put("turma", objTurma);

		return obj;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aluno/chamada/checkin", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_ALUNO" })
	@ResponseBody
	public JSONObject alunoChamadaCheckin(
			@ModelAttribute("usuario") Usuario usuario,
			@RequestBody Chamada chamada)
	{
		Aluno aluno = usuario.getAluno();
		chamada = chamadaDAO.find(chamada.getIdChamada());
		if (null == chamada)
		{
			throw new NoResultException("Chamada inexistente");
		}
		if (ChamadaState.aberta != chamada.getState())
		{
			throw new IllegalStateException("Chamada não está aberta.");
		}
		if (!chamada.getTurma().getAlunos().contains(aluno))
		{
			throw new NoResultException(
					"Aluno não pertence a turma desta chamada!");
		}

		Presenca presenca = presencaDAO.findPresencaChamadaAbertaAluno(
				chamada.getIdChamada(), aluno.getRaAluno());
		presenca.checkInPresenca();
		chamada = chamadaDAO.find(chamada.getIdChamada());

		// construir resposta JSON
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");

		JSONObject obj = new JSONObject();
		obj.put("idPresenca", presenca.getIdPresenca());
		obj.put("horaInicio", shf.format(presenca.getHoraInicio()));
		obj.put("numTicks", presenca.getNumTicks());

		JSONObject objChamada = new JSONObject();
		objChamada.put("idChamada", chamada.getIdChamada());
		objChamada.put("dataChamada", sdf.format(chamada.getDataChamada()));
		objChamada.put("horaInicio", shf.format(chamada.getHoraInicio()));
		objChamada.put("professorChamada", chamada.getProfessor().getUsuario()
				.getNome());
		obj.put("chamada", objChamada);

		Turma turma = chamada.getTurma();
		JSONObject objTurma = new JSONObject();
		objTurma.put("idTurma", turma.getIdTurma());
		objTurma.put("codTurma", turma.getCodTurma());
		objTurma.put("codDisciplina", turma.getDisciplina().getCodDisciplina());
		objTurma.put("nomeDisciplina", turma.getDisciplina()
				.getNomeDisciplina());
		obj.put("turma", objTurma);

		return obj;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aluno/chamada/tick", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_ALUNO" })
	@ResponseBody
	public JSONObject alunoReceberTick(
			@ModelAttribute("usuario") Usuario usuario,
			@RequestBody Presenca presenca)
	{
		Aluno aluno = usuario.getAluno();
		presenca = presencaDAO.find(presenca.getIdPresenca());
		if (presenca.getAluno().getRaAluno() != aluno.getRaAluno())
		{
			throw new AccessDeniedException(
					"Você não é o aluno deste tick de presença.");
		}

		// construir resposta JSON
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdhf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Tick tick = presenca.receberTick();
		tickDAO.persist(tick);

		JSONObject obj = new JSONObject();
		obj.put("idTick", tick.getIdTick());
		obj.put("dataHora", sdhf.format(tick.getDataHora()));

		JSONObject objPresenca = new JSONObject();
		objPresenca.put("idPresenca", presenca.getIdPresenca());
		objPresenca.put("horaInicio", shf.format(presenca.getHoraInicio()));
		objPresenca.put("numTicks", presenca.getNumTicks());
		obj.put("presenca", objPresenca);

		return obj;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aluno/chamada/checkout", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_ALUNO" })
	@ResponseBody
	public JSONObject alunoChamadaCheckout(
			@ModelAttribute("usuario") Usuario usuario,
			@RequestBody Presenca presenca)
	{
		Aluno aluno = usuario.getAluno();
		presenca = presencaDAO.find(presenca.getIdPresenca());
		if (presenca.getAluno().getRaAluno() != aluno.getRaAluno())
		{
			throw new AccessDeniedException(
					"Você não é o aluno deste tick de presença.");
		}
		presenca.checkOutPresenca();

		// construir resposta JSON
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONObject obj = new JSONObject();
		obj.put("idPresenca", presenca.getIdPresenca());
		obj.put("horaInicio", shf.format(presenca.getHoraInicio()));
		obj.put("horaFim", shf.format(presenca.getHoraFim()));
		obj.put("numTicks", presenca.getNumTicks());

		return obj;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.CONFLICT)  // 409
	public JSONObject handleError(HttpServletRequest req, Exception exception)
	{
		JSONObject obj = new JSONObject();
		obj.put("error", "exception");
		obj.put("message", exception.getMessage());
		return obj;
	}
}
