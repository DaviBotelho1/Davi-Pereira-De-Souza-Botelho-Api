package br.com.davi.pereira.de.souza.botelho.api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpanAPIConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		OpenAPI openAPI = new OpenAPI();
		Info info = new Info();
		info.title("Avaliação 03 - Acelera G&P");
		info.version("v0.0.1");
		info.description(
				"<h3> Esta é uma avaliação com o objetivo de Avaliar o desenvolvimento de REST API com documentação Swagger. </h3>");
		openAPI.info(info);
		openAPI.addTagsItem(new Tag().name("Lista").description("Gerencia as Listas do sistema"));
		openAPI.addTagsItem(new Tag().name("Item").description("Gerencia os itens do sistema"));
		return openAPI;
	}
}
