package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Classe de configuração para o uso
 * da OpenAPI 2 para documentação da API
 * 
 * @author Leonardo
 *
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDockey() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*")) //Especificar um end point específico
					.build()
				.apiInfo(apiInfo()) //Chama o método apiInfo para montar o cabeçalho da documentação
				.tags(tags()[0], tags());//Chama o método tags para alterar os nomes dos end points na documentacao
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Algafood API")
				.description("API aberta para clientes e restaurantes")
				.version("1.0")
				.contact(new Contact("Algaworks", "https://www.algaworks.com", "contato@algaworks.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Mapeamento dos arquivos do Swagger
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	private Tag[] tags() {
	    return new Tag[] {
	        new Tag("Cidades", "Gerencia as cidades"),
	        new Tag("Cozinhas", "Gerencia as cozinhas"),
	        new Tag("Grupos", "Gerencia os grupos"),
	        new Tag("Grupos de permissão", "Gerencia os grupos de permissões"),
	        new Tag("Pedidos", "Gerencia os pedidos"),
	        new Tag("Fluxo de pedidos", "Gerencia o fluxo de pedidos"),
	        new Tag("Formas de pagamento", "Gerencia os métodos de pagamento"),
	        new Tag("Restaurantes", "Gerencia the restaurants"),
	        new Tag("Formas de pagamento dos restaurantes", "Gerencia as formas de pagamento dos restaurantes"),
	        new Tag("Produtos dos restaurantes", "Gerencia os produtos dos restaurantes"),
	        new Tag("Estados", "Gerencia os Estados"),
	        new Tag("Estatísticas", "Gerencia as estatísticas"),
	        new Tag("Usuários", "Gerencia os usuários"),
	        new Tag("Grupos de usuários", "Gerencia os grupos de usuários"),
	        new Tag("Usuário responsável pelo restaurante", "Gerencia os usuários responsáveis pelo restaurante")
	    };
	}
}
