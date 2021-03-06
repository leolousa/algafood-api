package com.algaworks.algafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PagedModelOpenApi;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Classe de configura????o para o uso
 * da OpenAPI 2 para documenta????o da API
 * 
 * @author Leonardo
 *
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) // Para aproveitar as anota????es do Bean Validation na documenta????o (Nem todas est??o implementadas)
public class SpringFoxConfig implements WebMvcConfigurer {

	private TypeResolver typeResolver = new TypeResolver();
	
	@Bean
	public Docket apiDockey() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*")) //Especificar um end point espec??fico
					.build()
					.useDefaultResponseMessages(false) // Mensagens default das respostas do endpoint
					.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages()) // Mapeamento dos m??todos espec??ficos e seus retornos
					.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
		            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
		            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
		            .additionalModels(typeResolver.resolve(Problem.class)) //Lista um modelo extra na parte de Models da documenta????o
		            .ignoredParameterTypes(ServletWebRequest.class, URL.class,
		            			URI.class, URLStreamHandler.class, Resource.class,
		            			File.class, InputStream.class) //Ignora este tipo de objeto nas assinaturas dos m??todos
		            .alternateTypeRules(buildPageTypeRole(CozinhaModel.class))
		            .alternateTypeRules(buildPageTypeRole(PedidoResumoModel.class))
		            //.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaModel.class), CozinhasModelOpenApi.class))
		            .directModelSubstitute(Pageable.class, PageableModelOpenApi.class) // Sibstitui uma classe pela outra para refletir na documenta????o
				
		        .securitySchemes(Arrays.asList(securityScheme())) //Esquema de seguran??a para acessar a documenta????o com o Access Token
		        .securityContexts(Arrays.asList(securityContext()))
		        
				.apiInfo(apiInfo()) //Chama o m??todo apiInfo para montar o cabe??alho da documenta????o
				.tags(tags()[0], tags());//Chama o m??todo tags para alterar os nomes dos end points na documentacao
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Mapeamento dos arquivos do Swagger
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	//M??todo para utilizar os Access Tokens na documenta????o do Swagger
	private SecurityScheme securityScheme() {
		return new OAuthBuilder()
				.name("AlgaFood")
				.grantTypes(grantTypes())
				.scopes(scopes())
				.build();
	}

	//M??todo que descreve os caminhos da API que est??o protegidos
	private SecurityContext securityContext( ) {
		var securityReference = SecurityReference.builder()
				.reference("AlgaFood")
				.scopes(scopes().toArray(new AuthorizationScope[0]))
				.build();
		
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(securityReference))
				.forPaths(PathSelectors.any())
				.build();
	}
	
	private List<GrantType> grantTypes() {
		//Fluxo password flow
		return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
	}
	
	private List<AuthorizationScope> scopes() {
		return Arrays.asList(
				new AuthorizationScope("READ", "Acesso de leitura"),
				new AuthorizationScope("WRITE", "Acesso de escrita"));
	}
	
	//M??todo que descreve apenas os retornos de erros aos m??todos GET na documenta????o dos endpoints
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
					.build()					
			);
	}
	
	//M??todo que descreve apenas os retornos de erros aos m??todos POST e PUT na documenta????o dos endpoints
	private List<ResponseMessage> globalPostPutResponseMessages() {
	    return Arrays.asList(
	            new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisi????o inv??lida (erro do cliente)")
	                .responseModel(new ModelRef("Problema"))
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .responseModel(new ModelRef("Problema"))
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.NOT_ACCEPTABLE.value())
	                .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
	                .message("Requisi????o recusada porque o corpo est?? em um formato n??o suportado")
	                .responseModel(new ModelRef("Problema"))
	                .build()
	        );
	}

	//M??todo que descreve apenas os retornos de erros aos m??todos DELETE na documenta????o dos endpoints
	private List<ResponseMessage> globalDeleteResponseMessages() {
	    return Arrays.asList(
	            new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisi????o inv??lida (erro do cliente)")
	                .responseModel(new ModelRef("Problema"))
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .responseModel(new ModelRef("Problema"))
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
	        new Tag("Grupos de permiss??o", "Gerencia os grupos de permiss??es"),
	        new Tag("Pedidos", "Gerencia os pedidos"),
	        new Tag("Formas de pagamento", "Gerencia os m??todos de pagamento"),
	        new Tag("Restaurantes", "Gerencia os restaurantes"),
	        new Tag("Produtos", "Gerencia os produtos dos restaurantes"),
	        new Tag("Estados", "Gerencia os Estados"),
	        new Tag("Estat??sticas", "Gerencia as estat??sticas"),
	        new Tag("Usu??rios", "Gerencia os usu??rios"),
	        new Tag("Grupos de usu??rios", "Gerencia os grupos de usu??rios"),
	        new Tag("Usu??rio respons??vel pelo restaurante", "Gerencia os usu??rios respons??veis pelo restaurante")
	    };
	}
	
	// M??todo para simplificar o uso de alternateTypeRules na configura????o do Docket quando as Entidades
	// da API forem usar o objeto Page do Spring para pagina????o para a documenta????o da API
	private <T> AlternateTypeRule buildPageTypeRole(Class<T> classModel) {
        return AlternateTypeRules.newRule(
                typeResolver.resolve(Page.class, classModel), 
                typeResolver.resolve(PagedModelOpenApi.class, classModel)
        );
    }
}
