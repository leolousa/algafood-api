package com.algaworks.algafood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

/**
 * Classe de recurso 
 * @author Leonardo
 *
 */
public class ResourceUtils {

	/**
	 * Método que extrai de um arquivo JSON o
	 * seu conteúdo e coloca em uma String
	 * 
	 * @param resourceName
	 * @return
	 */
	public static String getContentFromResource(String resourceName) {
	    try {
	        InputStream stream = ResourceUtils.class.getResourceAsStream(resourceName);
	        return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	} 
}
