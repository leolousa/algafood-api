package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Estados")
@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;  
	
	@ApiOperation("Lista os Estados")
	@GetMapping
	public List<EstadoModel> listar() {
	    List<Estado> todosEstados = estadoRepository.findAll();
	    return estadoModelAssembler.toCollectionModel(todosEstados);
	}

	@ApiOperation("Busca um Estado por ID")
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
	    Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
	    return estadoModelAssembler.toModel(estado);
	}

	@ApiOperation("Cadastra um novo Estado")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
	    Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
	    estado = cadastroEstado.salvar(estado);
	    return estadoModelAssembler.toModel(estado);
	}

	@ApiOperation("Atualiza um Estado por ID")
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId,
	        @RequestBody @Valid EstadoInput estadoInput) {
	    Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
	    estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
	    estadoAtual = cadastroEstado.salvar(estadoAtual);
	    return estadoModelAssembler.toModel(estadoAtual);
	}       
	
	@ApiOperation("Exclui um Estado por ID")
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}
	
}