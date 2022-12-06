package com.evertonmota.autorizador;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage m201 = simpleMessage(201, "Criação com sucesso");
    private final ResponseMessage m422 = simpleMessage(422, "Este cartão já foi cadastrado.");
    private final ResponseMessage m200 = simpleMessage(200, "Não encontrado");
    private final ResponseMessage m404 = simpleMessage(404, "Cartão não existe");


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)

                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m200, m404))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m422))

                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.evertonmota.autorizador.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                " Mini autorizador -  VR Benefícios",
                "A VR processa todos os dias diversas transações de Vale Refeição e Vale Alimentação, entre outras.\n" +
                        "De forma breve, as transações saem das maquininhas de cartão e chegam até uma de nossas aplicações, conhecida como *autorizador*",
                "Versão 1.0",
                "",
                new Contact("Everton Mota", "https://www.linkedin.com/in/everton-mota-a9a73697/", "mcostagt@gmail.com"),
                "Permitido uso para estudantes",
                "https://www.linkedin.com/in/everton-mota-a9a73697/",
                Collections.emptyList() // Vendor Extensions
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }
}