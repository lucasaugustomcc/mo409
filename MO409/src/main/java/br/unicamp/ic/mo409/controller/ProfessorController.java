package br.unicamp.ic.mo409.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.ProfessorDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;

@Component
@RestController
public class ProfessorController {

	@Autowired
	UsuarioDAO usuarioDAO;

	@Autowired
	public ProfessorDAO professorDAO;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada", method = RequestMethod.GET, produces = "application/json")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray menu() {
		UserDetails usuario = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<Turma> turmas = professorDAO.listTurmasByProfessor(Integer.valueOf(usuario
				.getUsername()));

		JSONArray array = new JSONArray();
		for (Turma turma : turmas) {
			JSONObject obj = new JSONObject();
			obj.put("cod", turma.getCodTurma());
			obj.put("nome_disciplina", turma.getDisciplina()
					.getNomeDisciplina());
			array.add(obj);
		}
		return array;

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/turmas/chamada/abrir", method = RequestMethod.POST, consumes = "application/json")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray abrirChamada(@RequestBody List<Turma> turmas) {
		JSONArray array = new JSONArray();
		for (Turma turma : turmas)
		{
			JSONObject obj = new JSONObject();
			obj.put("turma_id", turma.getIdTurma());
			array.add(obj);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/abertura/turma/{turma_id}", method = RequestMethod.GET)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONObject verTurma(@PathVariable("turma_id") Long turma_id) {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("turma_id", turma_id);
		array.add(obj);
		return obj;
	}
}
