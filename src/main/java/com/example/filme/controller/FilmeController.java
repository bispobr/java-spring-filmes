package com.example.filme.controller;

import com.example.filme.model.Filme;
import com.example.filme.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/Filmes")
public class FilmeController {
    @Autowired
    private FilmeService filmeService;

    @GetMapping
    @Operation(description = "Endpoint responsável por listar dados do filme")
    @ApiResponse(responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public List<Filme> listarfilmes(@RequestParam Optional<String> titulo){
        log.info("Requisição de listagem recebida");
        return titulo.map(filmeService::FiltrobyTitulo).orElseGet(filmeService::pegarTodosFilmes);
    }

    @GetMapping ("/prefixo")
    @Operation(description = "Endpoint responsável por listar dados do filme atraves do prefixo")
    @ApiResponse(responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public List<String> autocompletar(@RequestParam("prefixo") String prefixo ){
        log.info("Requisição de listagem recebida");
        return filmeService.prefixo(prefixo);
    }

    @GetMapping("/diretor")
    @Operation(description = "Endpoint responsável por listar filme feitos pelo diretor")
    @ApiResponse(responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public List<Filme> listarfilmesDiretor(@RequestParam Optional<String> diretor){
        log.info("Requisição de listagem recebida");
        return diretor.map(filmeService::FiltrobyDiretor).orElseGet(filmeService::pegarTodosFilmes);
    }
}
