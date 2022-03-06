package org.generation.blogpessoallg.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.generation.blogpessoallg.model.UserLoginModel;
import org.generation.blogpessoallg.model.UsuarioModel;
import org.generation.blogpessoallg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.codec.binary.Base64; //PARA A BASE 64
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

		@Autowired
		private UsuarioRepository repository;
		
		public UsuarioModel CadastrarUsuario(UsuarioModel usuario) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return repository.save(usuario);
		}
		
		
		public Optional<UserLoginModel> Logar(Optional<UserLoginModel> user){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Optional<UsuarioModel> usuario = repository.findByUsuario(user.get().getUsuario());
			
			if(usuario.isPresent()) {
				if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
					
					String auth = user.get().getUsuario() + ":" + user.get().getSenha();
					byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
					String authHeader = "Basic " + new String(encodedAuth);
					
					user.get().setToken(authHeader);
					user.get().setNome(usuario.get().getNome());
					return user;
				}
			}
			return null;
		}
				
}