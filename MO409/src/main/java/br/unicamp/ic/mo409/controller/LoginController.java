package br.unicamp.ic.mo409.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Usuario;

@Component
@RestController
public class LoginController {

	@Autowired
	UsuarioDAO usuarioDAO;

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Usuario login() {
		Authentication auth = (Authentication) SecurityContextHolder
				.getContext().getAuthentication();
		UserDetails usuario = (UserDetails) auth.getPrincipal();
		Usuario user = usuarioDAO.loadUsuarioByUsername(usuario.getUsername());
			
		return user;
	}
}
