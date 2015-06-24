package br.unicamp.ic.mo409.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

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
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.model.Usuario;

@Component
@RestController
public class ProfessorController {

	@Autowired
	UsuarioDAO usuarioDAO;

	@Autowired
	ChamadaDAO chamadaDAO;

	@Autowired
	ProfessorDAO professorDAO;
	
	@Autowired
	TurmaDAO turmaDAO;
	
	@ModelAttribute("usuario")
    public Usuario getUsuario() {
		
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
	public JSONArray turmasChamada(@ModelAttribute("usuario") Usuario usuario) {

		List<Turma> turmas = turmaDAO.listarTurmasProfessor(Integer
				.valueOf(usuario.getProfessor().getRaProfessor()));
		

		// construir resposta JSON
		JSONArray array = new JSONArray();
		for (Turma turma : turmas) {
			JSONObject obj = new JSONObject();
			obj.put("idTurma", turma.getIdTurma());
			obj.put("codTurma", turma.getCodTurma());
			obj.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			obj.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			array.add(obj);
		}
		return array;		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/abrir", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray abrirChamada(@ModelAttribute("usuario") Usuario usuario, @RequestBody List<Turma> turmas) throws Exception{

		Professor professor = professorDAO.find(usuario.getProfessor().getRaProfessor());			

		// construindo JSON de resposta
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		for (Turma turma : turmas) 
		{
			turma = turmaDAO.find(turma.getIdTurma());
			if (turma == null)
			{
				throw new Exception("Turma não existente");
			}
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException("Professor não associado a turma.");
			}			
						
			// abrir chamada
			Chamada chamada = new Chamada();
			chamada.abrirChamada(turma, professor,
					new Date(System.currentTimeMillis()),
					new Time(System.currentTimeMillis()));
			chamadaDAO.persist(chamada);
			
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
			objTurma.put("codDisciplina", turma.getDisciplina().getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina().getNomeDisciplina());
			obj.put("turma",objTurma);
			array.add(obj);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/encerrar", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray encerrarChamada(@ModelAttribute("usuario") Usuario usuario, @RequestBody List<Chamada> chamadas) {
				
		Professor professor = usuario.getProfessor();
				
		// construir resposta JSON
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		for (Chamada chamada : chamadas) 
		{
			chamada = chamadaDAO.find(chamada.getIdChamada());
			chamada.encerrarChamada(new Time(System.currentTimeMillis()));
			
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("horaFim", shf.format(chamada.getHoraInicio()));
			
			// obtendo dados da turma
			Turma turma = chamada.getTurma();
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException("Professor não associado a turma.");
			}	
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina().getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina().getNomeDisciplina());
			obj.put("turma",objTurma);
			array.add(obj);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/relatorio", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray relatorioChamada(@ModelAttribute("usuario") Usuario usuario, @RequestBody List<Chamada> chamadas) {
		
		Professor professor = usuario.getProfessor();
		
		// construir resposta JSON
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		
		for (Chamada chamada : chamadas) 
		{
			// obtendo objeto chamada do banco
			chamada = chamadaDAO.find(chamada.getIdChamada());
			chamada.encerrarChamada(new Time(System.currentTimeMillis()));						
			
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("horaFim", shf.format(chamada.getHoraInicio()));
						
			// obtendo dados da turma
			Turma turma = chamada.getTurma();	
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException("Professor não associado a turma.");
			}	
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina().getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina().getNomeDisciplina());
			obj.put("turma",objTurma);
			
			JSONArray arrayAlunos = new JSONArray();
			
			// obtendo alunos de cada turma
			List<Aluno> alunos = turma.getAlunos();
			for (Aluno aluno : alunos)
			{				
				JSONObject objAluno = new JSONObject();
				objAluno.put("raAluno",aluno.getRaAluno());
				objAluno.put("nomeAluno",aluno.getUsuario().getNome());
				arrayAlunos.add(objAluno);
			}
			obj.put("alunos",arrayAlunos);
			
			array.add(obj);
		}
		return array;
	}
}
