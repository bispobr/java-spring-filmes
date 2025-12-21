package com.example.filme.service;

import com.example.filme.dto.BuscaRequisicaoDTO;
import com.example.filme.model.Filme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmeService {

    private final WebClient webClient;

    public FilmeService (WebClient.Builder builder){
        log.info("Acessando Base Principal");
        this.webClient = builder.baseUrl("https://data.sfgov.org/resource/yitu-d5am.json").build();
    }

    @Cacheable(value = "dados")
    public List<Filme> pegarTodosFilmes(){
        log.info("Listagem concluida");
        return  webClient.get().retrieve().bodyToFlux(Filme.class).collectList().block();
    }

    public  List<Filme> FiltrobyTitulo(BuscaRequisicaoDTO busca){
        log.info("filtro por titulo");
        return  pegarTodosFilmes().stream().filter(f ->f.getTitle() != null && f.getTitle().toLowerCase().contains(busca.busca().toLowerCase())).collect(Collectors.toList());
    }

    public  List<String> prefixo(BuscaRequisicaoDTO prefixo){
        log.info("filtro por prefixo");
        return pegarTodosFilmes().stream().map(Filme::getTitle).filter(Objects::nonNull).filter(t->t.toLowerCase().startsWith(prefixo.busca().toLowerCase())).distinct().sorted().limit(10).collect(Collectors.toList());
    }

    public  List<Filme> FiltrobyDiretor(BuscaRequisicaoDTO busca){
        log.info("filtro por diretor");
        return  pegarTodosFilmes().stream().filter(f ->f.getDirector() != null && f.getDirector().toLowerCase().contains(busca.busca().toLowerCase())).collect(Collectors.toList());
    }
}
