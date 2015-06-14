package br.unicamp.ic.mo409.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.AlunoDAO;

@Component
@RestController
public class AlunoController {

	@Autowired
	public AlunoDAO alunoDAO;

	
	@RequestMapping("/aluno/menu")
	@Secured({"ROLE_ALUNO"})
	public String menu() {
		
		System.out.println("Executando a lógica com Spring MVC");

		return "aluno/menu";
	}
}
