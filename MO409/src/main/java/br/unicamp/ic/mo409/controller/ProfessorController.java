package br.unicamp.ic.mo409.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/turmas", method = RequestMethod.GET, produces = "application/json")
	@Secured({ "ROLE_PROFESSOR" })
	@PreAuthorize(value = "")
	@ResponseBody
	public JSONArray turmasChamada() {
		Authentication auth = (Authentication) SecurityContextHolder
				.getContext().getAuthentication();
		
		System.out.println("usuario" + auth);
		if (auth != null) {
			UserDetails usuario = (UserDetails) auth.getPrincipal();
			System.out.println("usuario"+usuario.getUsername());

			List<Turma> turmas = professorDAO.listTurmasByProfessor(Integer
					.valueOf(usuario.getUsername()));

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
		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/abrir", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray abrirChamada(@RequestBody List<Turma> turmas) {
		Authentication auth = (Authentication) SecurityContextHolder
				.getContext().getAuthentication();

		UserDetails usuario = (UserDetails) auth.getPrincipal();
		Usuario user = usuarioDAO.find(Integer.valueOf(usuario
				.getUsername()));
		Professor professor = professorDAO.find(user.getProfessor().getRaProfessor());

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		for (Turma turma : turmas) {
			turma = turmaDAO.find(turma.getIdTurma());
			Chamada chamada = new Chamada();
			chamada.abrirChamada(turma, professor,
					new Date(System.currentTimeMillis()),
					new Time(System.currentTimeMillis()));
			chamadaDAO.persist(chamada);
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
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
	@RequestMapping(value = "/professor/chamada/encerrar", method = RequestMethod.POST, consumes = "application/json")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray encerrarChamada(@RequestBody List<Chamada> chamadas) {
		Authentication auth = (Authentication) SecurityContextHolder
				.getContext().getAuthentication();

		UserDetails usuario = (UserDetails) auth.getPrincipal();
		Professor professor = professorDAO.find(Integer.valueOf(usuario
				.getUsername()));

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		for (Chamada chamada : chamadas) {
			chamada = chamadaDAO.find(chamada.getIdChamada());
			chamada.encerrarChamada(new Time(System.currentTimeMillis()));
			Turma turma = chamada.getTurma();
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("horaFim", shf.format(chamada.getHoraInicio()));
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
	public JSONArray relatorioChamada(@RequestBody List<Chamada> chamadas) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		
		for (Chamada chamada : chamadas) {
			chamada = chamadaDAO.find(chamada.getIdChamada());
			chamada.encerrarChamada(new Time(System.currentTimeMillis()));
			Turma turma = chamada.getTurma();
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("horaFim", shf.format(chamada.getHoraInicio()));
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina().getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina().getNomeDisciplina());
			obj.put("turma",objTurma);
			
			JSONArray arrayAlunos = new JSONArray();
			
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
