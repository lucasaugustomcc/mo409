package br.unicamp.ic.mo409.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.ProfessorDAO;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;

@Component
@RestController
public class ProfessorController {

	@Autowired
	public ProfessorDAO professorDAO;

	@RequestMapping(value="/professor/menu", 
			method = RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public JSONArray menu() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails details = (UserDetails) authentication.getPrincipal();
			String username = details.getUsername();
			System.out.println(username);
		}
		System.out.println("Executando a lógica com Spring MVC");
		
		Professor professor = professorDAO.find(161255);
		List<Turma> turmas = professorDAO.listTurmasByProfessor(professor);
		
		JSONArray array = new JSONArray();
		for (Turma turma : turmas)
		{
			JSONObject obj = new JSONObject();
			obj.put("cod", turma.getCodTurma());
			obj.put("nome_disciplina", turma.getDisciplina().getNomeDisciplina());
			array.add(obj);
		}
		return array;
	}
}
