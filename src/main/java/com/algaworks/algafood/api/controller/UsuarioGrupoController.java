package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;
    
    @ApiOperation("Lista os grupos de usuários")
    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
        
        return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
    }
    
    @ApiOperation("Exclui um usuário por ID")
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
    }
    
    @ApiOperation("Associa um usuário por ID a um grupo por ID")
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.associarGrupo(usuarioId, grupoId);
    }        
}
