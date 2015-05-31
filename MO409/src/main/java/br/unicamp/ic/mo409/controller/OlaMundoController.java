package br.unicamp.ic.mo409.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.UserDAO;
import br.unicamp.ic.mo409.model.User;

@Component
@RestController
public class OlaMundoController {

	@Autowired
	public UserDAO dao;

	@RequestMapping("/casa/olaMundoSpring")
	public String execute() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails details = (UserDetails) authentication.getPrincipal();
			String username = details.getUsername();
		}
		List<User> lista = dao.findAll();
		System.out.println("Executando a lógica com Spring MVC");

		return "olaMundo";
	}
}
