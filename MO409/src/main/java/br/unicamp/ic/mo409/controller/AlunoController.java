package br.unicamp.ic.mo409.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.AlunoDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Usuario;

@Component
@RestController
public class AlunoController {

	@Autowired
	public AlunoDAO alunoDAO;
	
	@Autowired
	UsuarioDAO usuarioDAO;

	@ModelAttribute("usuario")
    public Usuario getUsuario() {
		
		Authentication auth = (Authentication) SecurityContextHolder
				.getContext().getAuthentication();
		
		UserDetails user = (UserDetails) auth.getPrincipal();
		Usuario usuario = usuarioDAO.loadUsuarioByUsername(user.getUsername());
		
        return usuario;
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aluno/chamada", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_ALUNO" })
	@ResponseBody
	public JSONObject alunoChamada() {		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aluno/chamada/checkin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_ALUNO" })
	@ResponseBody
	public JSONObject alunoChamadaCheckin() {		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aluno/chamada/checkout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_ALUNO" })
	@ResponseBody
	public JSONObject alunoChamadaCheckout() {		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/aluno/chamada/tick", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_ALUNO" })
	@ResponseBody
	public JSONObject alunoChamadaReceberTick() {		
		return null;
	}
}
