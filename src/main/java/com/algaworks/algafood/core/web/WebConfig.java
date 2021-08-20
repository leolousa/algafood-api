package com.algaworks.algafood.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Classe que ativa o CORS na API
 * Permitindo acesso a requisições de origem cruzada
 * 
 * @author Leonardo
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Permite qualquer origem
		.allowedMethods("*");
		//.allowedOrigins("https://www.algafood.com") Permitir uma origem específica
		//.allowedMethods("GET", "HEAD", "POST") Permitir métodos específicos
		// registry.addMapping("/**");
	}
	
}
