package br.unicamp.ic.mo409.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import br.unicamp.ic.mo409.dao.UsuarioDAO;

public class UserAuthenticationProvider implements AuthenticationProvider, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015939741569160626L;
	
	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		boolean result = usuarioDAO.isUsuarioValido(authentication.getPrincipal()
				.toString(), authentication.getCredentials().toString());
		
		if (result) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new GrantedAuthority() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 9204098994910887104L;

				@Override
				public String getAuthority() {
					// TODO Auto-generated method stub
					return "ROLE_USER";
				}
			});
			UserAuthenticationToken auth = new UserAuthenticationToken(authentication.getPrincipal(),
					authentication.getCredentials(), grantedAuthorities);

			return auth;
		} else {
			throw new BadCredentialsException("Bad User Credentials.");
		}
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}
