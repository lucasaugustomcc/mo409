package br.unicamp.ic.mo409.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.UserDAO;
import br.unicamp.ic.mo409.model.User;

@ComponentScan
@RestController
public class LoginController {

	@Autowired
	public UserDAO dao;

	@RequestMapping("login")
	public String efetuaLogin(User usuario, HttpSession session,
			BindingResult result) {
		if (result.hasErrors())
			return "loginForm";

		if (dao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		return "loginForm";
	}

	@RequestMapping(value = "profile", method = RequestMethod.GET)
	@ResponseBody
	public String criarUsuario(User usuario, BindingResult result) {
		if (result.hasErrors())
			return "redirect:loginForm";
		User user1 = new User();
		user1.setName("lucas");
		user1.setLogin("lucas");
		user1.setPassword("oi");
		dao.persist(user1);
		JSONArray userArray = new JSONArray();
		List<User> usuarios = dao.findAll();
		for (User user : usuarios) {
			JSONObject userJSON = new JSONObject();
			userJSON.put("id", user.get_Id());
			userJSON.put("firstName", user.getName());
			userJSON.put("senha", user.getPassword());
			userArray.add(userJSON);
		}
		return userArray.toString();
	}

	@RequestMapping("loginfailed")
	public void loginForm(HttpServletResponse response) throws IOException {
		JSONArray userArray = new JSONArray();
		JSONObject userJSON = new JSONObject();
		User usuario = dao.find(1);
		userJSON.put("id", usuario.get_Id());
		userJSON.put("firstName", usuario.getName());
		userJSON.put("senha", usuario.getPassword());
		userArray.add(userJSON);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		response.getWriter().write(userArray.toString());
	}

	@RequestMapping("/")
	public String logout(HttpSession session) {
		session.invalidate();
		return "ok";
	}

}
