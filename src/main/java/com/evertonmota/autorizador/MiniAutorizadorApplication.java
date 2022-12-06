package com.evertonmota.autorizador;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import jdk.javadoc.doclet.Doclet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "API REST Mini autorizador - VR Benefícios",
				version = "1.0.0",
				description = "A VR processa todos os dias diversas transações de Vale Refeição e Vale Alimentação, entre outras.\n" +
						"De forma breve, as transações saem das maquininhas de cartão e chegam até uma de nossas aplicações, conhecida como *autorizador*"),
				servers = {
					@Server(url = "http://localhost:8080"),
				}
)
public class MiniAutorizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniAutorizadorApplication.class, args);
	}

}
