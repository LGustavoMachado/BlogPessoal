package org.generation.blogpessoallg.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogpessoallg.model.TemaModel;
import org.generation.blogpessoallg.repository.TemaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TemaControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private TemaRepository temaRepository;
	
	@BeforeAll
	void start() {
		
		temaRepository.deleteAll();
		
	}

	@Test
	@Order(1)
	@DisplayName("Cadastrar Um Usuário")
	public void deveCriarUmTema() {

		HttpEntity<TemaModel> requisicao = new HttpEntity<TemaModel>(new TemaModel(0L,
				"Linguagem Java"));

		ResponseEntity<TemaModel> resposta = testRestTemplate
				.withBasicAuth("root", "root")		
				.exchange("/temas", HttpMethod.POST, requisicao, TemaModel.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getDescricao(), resposta.getBody().getDescricao());
	}

	@Test
	@Order(2)
	@DisplayName("Alterar um Usuário")
	public void deveAtualizarUmTema() {

		TemaModel temaCreate = temaRepository.save(new TemaModel(0L,
				"Linguagem PHP"));

		TemaModel temaUpdate = new TemaModel(temaCreate.getId(),
				"Linguagem Phyton");

		HttpEntity<TemaModel> requisicao = new HttpEntity<TemaModel>(temaUpdate);

		ResponseEntity<TemaModel> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/temas", HttpMethod.PUT, requisicao, TemaModel.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(temaUpdate.getDescricao(), resposta.getBody().getDescricao());

	}

	@Test
	@Order(3)
	@DisplayName("Listar todos os Usuários")
	public void deveMostrarTodosTemas() {

		temaRepository.save(new TemaModel(0L,
				"Linguagem GO"));

		temaRepository.save(new TemaModel(0L,
				"Linguagem R"));

		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/temas", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@Order(4)
	@DisplayName("Apagar um Tema")
	public void deveApagarUmTema() {

		TemaModel temaDelete = temaRepository.save(new TemaModel(0L,
				"Linguagem JavaScript"));

		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/temas/" + temaDelete.getId(), HttpMethod.DELETE, null, String.class);

		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	}

}

