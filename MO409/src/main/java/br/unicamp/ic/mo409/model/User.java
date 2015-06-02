package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable, UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 640132268942204964L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "{tarefa.descricao.vazia}")
	@Size(min = 5, message = "{tarefa.descricao.pequena}")
	String username = null;
	String password = null;
	String email = null;
	Boolean enabled = true;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int get_Id() {
		return id;
	}

	public void set_Id(int id) {
		this.id = id;
	}

	public String getUserame() {
		return username;
	}

	public void setName(String Name) {
		this.username = Name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String Password) {
		this.password = Password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
