package br.unicamp.ic.mo409.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Usuario;

@Component
@RestController
public class OlaMundoController {

	@Autowired
	public UsuarioDAO usuarioDAO;

	@RequestMapping("/casa/olaMundoSpring")
	public String execute() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails details = (UserDetails) authentication.getPrincipal();
			String username = details.getUsername();
		}
		List<Usuario> lista = usuarioDAO.findAll();
		System.out.println("Executando a lógica com Spring MVC");

		return "olaMundo";
	}
}
