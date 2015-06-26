package br.unicamp.ic.mo409.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.ChamadaDAO;
import br.unicamp.ic.mo409.dao.ProfessorDAO;
import br.unicamp.ic.mo409.dao.TurmaDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.ChamadaState;
import br.unicamp.ic.mo409.model.Presenca;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.model.Usuario;

@Component
@RestController
@Transactional
public class ProfessorController
{

	@Autowired
	UsuarioDAO usuarioDAO;

	@Autowired
	ChamadaDAO chamadaDAO;

	@Autowired
	ProfessorDAO professorDAO;

	@Autowired
	TurmaDAO turmaDAO;

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
	@RequestMapping(value = "/professor/chamada/turmas", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray turmasChamada(@ModelAttribute("usuario") Usuario usuario)
	{

		List<Turma> turmas = turmaDAO.listarTurmasProfessor(Integer
				.valueOf(usuario.getProfessor().getRaProfessor()));

		// construir resposta JSON
		JSONArray array = new JSONArray();
		for (Turma turma : turmas)
		{
			JSONObject obj = new JSONObject();
			obj.put("idTurma", turma.getIdTurma());
			obj.put("codTurma", turma.getCodTurma());
			obj.put("codDisciplina", turma.getDisciplina().getCodDisciplina());
			obj.put("nomeDisciplina", turma.getDisciplina().getNomeDisciplina());
			array.add(obj);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/abrir", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray abrirChamada(@ModelAttribute("usuario") Usuario usuario,
			@RequestBody List<Turma> turmas) throws Exception
	{
			
		for (Turma turma : turmas)
		{
			if (chamadaDAO.hasChamadaAbertaTurma(turma.getIdTurma()))
			{
				throw new Exception("Já existe uma chamada aberta para esta turma");
			}
		}
		
		Professor professor = usuario.getProfessor();

		// construindo JSON de resposta
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		
		for (Turma turma : turmas)
		{
			turma = turmaDAO.find(turma.getIdTurma());
			if (turma == null)
			{
				throw new NoResultException("Turma não existente");
			}
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}

			// abrir chamada
			Chamada chamada = new Chamada();
			List<Presenca> presencas = chamada.abrirChamada(turma, professor,
					new Date(System.currentTimeMillis()),
					new Time(System.currentTimeMillis()));
			chamadaDAO.persist(chamada);
			chamada.setPresencas(presencas);
			

			// construir resposta JSON
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));

			// dados da turma
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/encerrar", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray encerrarChamada(
			@ModelAttribute("usuario") Usuario usuario,
			@RequestBody List<Chamada> chamadas)
	{

		Professor professor = usuario.getProfessor();

		// construir resposta JSON
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		for (Chamada chamada : chamadas)
		{
			chamada = chamadaDAO.find(chamada.getIdChamada());
			try
			{
				chamada.encerrarChamada(new Time(System.currentTimeMillis()));
			}
			catch(IllegalStateException e)
			{
				// TODO: mensagem para usuário em JSON
			}
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("horaFim", shf.format(chamada.getHoraFim()));

			// obtendo dados da turma
			Turma turma = chamada.getTurma();
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/relatorio", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray relatorioChamada(
			@ModelAttribute("usuario") Usuario usuario,
			@RequestBody List<Chamada> chamadas) throws Exception
	{

		Professor professor = usuario.getProfessor();

		// construir resposta JSON
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();

		for (Chamada chamada : chamadas)
		{
			// obtendo objeto chamada do banco
			
			chamada = chamadaDAO.find(chamada.getIdChamada());
			if (null == chamada)
			{
				// TODO: mensagem de erro no JSON
				throw new NoResultException("Chamada inexistente");
			}	
			if (ChamadaState.encerrada != chamada.getState())
			{
				// TODO: mensagem de erro no JSON
				throw new Exception("Chamada não encerrada ainda. Resultado da presença inexistente");
			}	

			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("horaFim", shf.format(chamada.getHoraFim()));

			// obtendo dados da turma
			Turma turma = chamada.getTurma();
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);

			JSONArray arrayPresencas = new JSONArray();

			// obtendo relatorio de presença dos alunos de cada chamada
			List<Presenca> presencas = chamada.getPresencas();
			for (Presenca presenca : presencas)
			{
				Aluno aluno = presenca.getAluno();
				JSONObject objPresenca = new JSONObject();
				objPresenca.put("raAluno", aluno.getRaAluno());
				objPresenca.put("nomeAluno", aluno.getUsuario().getNome());
				objPresenca.put("statusPresenca", presenca.getIsPresente());
				arrayPresencas.add(objPresenca);
			}
			obj.put("alunos", arrayPresencas);

			array.add(obj);
		}
		return array;
	}
}
