package com.evertonmota.autorizador;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class MiniAutorizadorApplicationTests {

	@Autowired
	TestRestTemplate client ;

	@Test
	void contextLoads() {

		ResponseEntity<String> response = client.postForEntity("http://localhost:8080/cartoes", """
				{
				
				    "numeroCartao": "6549873025634501",
				    "senha": "1234"
				}""", String.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

		Card card = this.cardRepository.getByNumeroCartao("6549873025634501");
		Assertions.assertEquals(500,card.getSaldo());
	}

}
