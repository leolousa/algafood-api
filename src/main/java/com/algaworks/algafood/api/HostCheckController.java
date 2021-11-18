package com.algaworks.algafood.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para retornar o IP e Nome da API
 * que está respondendo à requisição - Teste de
 * Poor Man's Load Balancer (DNS Round Robin) 
 * @author Leonardo
 *
 */
@RestController
public class HostCheckController {

	@GetMapping("/hostcheck")
	public String checkHost() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress()
				+ " - " + InetAddress.getLocalHost().getHostName();
	}
}
