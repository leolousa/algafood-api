package com.algaworks.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
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
@Import(BeanValidatorPluginsConfiguration.class) // Para aproveitar as anotações do Bean Validation na documentação (Nem todas estão implementadas)
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDockey() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*")) //Especificar um end point específico
					.build()
					.useDefaultResponseMessages(false) // Mensagens default das respostas do endpoint
					.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages()) // Mapeamento dos métodos específicos e seus retornos
					.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
		            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
		            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
		            .additionalModels(typeResolver.resolve(Problem.class)) //Lista um modelo extra na parte de Models da documentação
				.apiInfo(apiInfo()) //Chama o método apiInfo para montar o cabeçalho da documentação
				.tags(tags()[0], tags());//Chama o método tags para alterar os nomes dos end points na documentacao
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Mapeamento dos arquivos do Swagger
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	//Método que descreve apenas os retornos de erros aos métodos GET na documentação dos endpoints
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build()					
			);
	}
	
	//Método que descreve apenas os retornos de erros aos métodos POST e PUT na documentação dos endpoints
	private List<ResponseMessage> globalPostPutResponseMessages() {
	    return Arrays.asList(
	            new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisição inválida (erro do cliente)")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.NOT_ACCEPTABLE.value())
	                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
	                .message("Requisição recusada porque o corpo está em um formato não suportado")
	                .build()
	        );
	}

	//Método que descreve apenas os retornos de erros aos métodos DELETE na documentação dos endpoints
	private List<ResponseMessage> globalDeleteResponseMessages() {
	    return Arrays.asList(
	            new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisição inválida (erro do cliente)")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .build()
	        );
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Algafood API")
				.description("API aberta para clientes e restaurantes")
				.version("1.0")
				.contact(new Contact("Algaworks", "https://www.algaworks.com", "contato@algaworks.com"))
				.build();
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
