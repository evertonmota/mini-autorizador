package com.evertonmota.autorizador;

import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.repository.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Este Teste funcional depende do banco rodando.
@SpringBootTest
class MiniAutorizadorApplicationTests {

	@Autowired
	TestRestTemplate client ;
	private CardRepository cardRepository;

	@Test
	void contextLoads() {
	//SETUP
		//execução
		ResponseEntity<String> response = client.postForEntity("http://localhost:8080/cartoes", """
				{
				
				    "numeroCartao": "6549873025634501",
				    "senha": "1234"
				}""", String.class);
		//verificação
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

		Card card = this.cardRepository.getByNumeroCartao("6549873025634501");
		Assertions.assertEquals(500,card.getSaldo());
	}

}
