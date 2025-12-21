package com.example.filme.controller;

import com.example.filme.dto.BuscaRequisicaoDTO;
import com.example.filme.dto.BuscaRespostaDTO;
import com.example.filme.dto.FilmeRespostaDTO;
import com.example.filme.mapper.FilmeMapper;
import com.example.filme.model.Filme;
import com.example.filme.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Slf4j
@RestController
@RequestMapping("/Filmes")
public class FilmeController {
    @Autowired
    private FilmeService filmeService;

    @Autowired
    FilmeMapper mapper;

    @GetMapping("/TodosFilmes")
    @Operation(description = "Endpoint responsável por listar Todos os filmes do acervo")
    @ApiResponse(responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<List<FilmeRespostaDTO>> listarfilmes(){
        log.info("Requisição para Listar todos os filmes recebida");
        return ResponseEntity.ok().body(mapper.paraRespostaList(filmeService.pegarTodosFilmes()));
    }

    @PostMapping("/titulo")
    @Operation(description = "Endpoint responsável por listar filmes por titulo e seus dados presente no acervo")
    @ApiResponse(responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "400", description = "Erro de Requisição")
    @ApiResponse(responseCode = "404", description = "Não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<Object> listarfilmesTitulo(@RequestBody @Valid BuscaRequisicaoDTO dto){

        log.info("Requisição para listar Filmes por titulo  recebida");
        List<Filme> encontrado = filmeService.FiltrobyTitulo(dto);
        if (encontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(mapper.paraRespostaList(encontrado)) ;

    }

    @PostMapping("/buscaprefixo")
    @Operation(description = "Endpoint responsável por listar titulos de filmes do acervo baseado em um prefixo")
    @ApiResponse(responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "400", description = "Erro de Requisição")
    @ApiResponse(responseCode = "404", description = "Não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<List<BuscaRespostaDTO>> prefixo(@RequestBody @Valid BuscaRequisicaoDTO prefixo ){
        log.info("Requisição de busca por titulo  recebida");
        List<String> encontrado = filmeService.prefixo(prefixo);
        if (encontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(mapper.paraRespostaBuscaList(encontrado));
    }

    @PostMapping("/diretor")
    @Operation(description = "Endpoint responsável por listar filme feitos pelo diretor")
    @ApiResponse(responseCode = "200", description = "Listagem bem sucedida")
    @ApiResponse(responseCode = "400", description = "Erro de Requisição")
    @ApiResponse(responseCode = "404", description = "Não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<List<FilmeRespostaDTO>> listarfilmesDiretor(@RequestBody @Valid BuscaRequisicaoDTO diretor){
        log.info("Requisição de listagem recebida");
        List<Filme> encontardo = filmeService.FiltrobyDiretor(diretor);

        if (encontardo.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(mapper.paraRespostaList(encontardo));
    }
}
