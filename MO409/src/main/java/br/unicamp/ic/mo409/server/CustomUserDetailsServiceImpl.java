package br.unicamp.ic.mo409.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.unicamp.ic.mo409.dao.UserDAO;
import br.unicamp.ic.mo409.model.User;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		User user = userDAO.findUserByEmail(email);

		if (user == null) {
			// LOGGER.error("No user found with username: " + username);
			throw new UsernameNotFoundException("No user found with username: "
					+ email);
		}
		// TODO change
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				user.getAuthorities());

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return userDetails;
	}

	public Collection getAuthorities(Role role) {
		// public Collection<!--? extends GrantedAuthority-->
		// getAuthorities(Long role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	// TODO AMELIORER
	public List<String> getRoles(Role role) {

		List<String> roles = new ArrayList<String>();

		if (role.equals("ROLE_ADMIN")) {
			roles.add("ROLE_ADMIN");
			roles.add("ROLE_USER");

		} else if (role.equals("ROLE_USER")) {
			roles.add("ROLE_USER");
		}
		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
