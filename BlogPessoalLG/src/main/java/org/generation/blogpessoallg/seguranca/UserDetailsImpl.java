package org.generation.blogpessoallg.seguranca;

import java.util.Collection;

import org.generation.blogpessoallg.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Clica no userDetails para invocar os métodos dele
public class UserDetailsImpl implements UserDetails {

	public static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	//Construtor para pegar o usuario e senha
	public UserDetailsImpl(UsuarioModel user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl() {
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {	
		return true;
	}

}
