package com.example.filme.service;

import com.example.filme.dto.BuscaRequisicaoDTO;
import com.example.filme.model.Filme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmeServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private FilmeService filmeService;

    @BeforeEach
    void setUp() {
        webClient = mock(WebClient.class);
        WebClient.Builder builder = mock(WebClient.Builder.class);
        requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        responseSpec = mock(WebClient.ResponseSpec.class);

        when(builder.baseUrl(any())).thenReturn(builder);
        when(builder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);

        filmeService = new FilmeService(builder);
    }

    @Test
    void deveRetornarTodosFilmes() {
        Filme filme1 = new Filme("Filme A","1958","AVENIDA","FIC","tv","director","bell","bia","tia","123456","654872");
        Filme filme2 = new Filme("Filme W","1928","AVEA","FICts","tvT","directors","belly","bianca","tiara","123456","654872");

        when(responseSpec.bodyToFlux(Filme.class)).thenReturn(Flux.just(filme1, filme2));

        List<Filme> filmes = filmeService.pegarTodosFilmes();

        assertNotNull(filmes);
        assertEquals(2, filmes.size());
        assertEquals("Filme A", filmes.getFirst().getTitle());
    }

    @Test
    void deveFiltrarFilmesPorTitulo() {
        Filme filme1 = new Filme("Titanic","1958","AVENIDA","FIC","tv","director","bell","bia","tia","123456","654872");
        Filme filme2 = new Filme("Inception","1928","AVEA","FICts","tvT","directors","belly","bianca","tiara","123456","654872");

        when(responseSpec.bodyToFlux(Filme.class)).thenReturn(Flux.just(filme1, filme2));

        List<Filme> resultado = filmeService.FiltrobyTitulo(new BuscaRequisicaoDTO("tita"));

        assertEquals(1, resultado.size());
        assertEquals("Titanic", resultado.getFirst().getTitle());
    }

    @Test
    void deveFiltrarFilmesPorDiretor() {
        Filme filme1 = new Filme("Titanic","1958","AVENIDA","FIC","tv","James Cameron","bell","bia","tia","123456","654872");
        Filme filme2 = new Filme("Inception","1928","AVEA","FICts","tvT","Christopher Nolan","belly","bianca","tiara","123456","654872");

        when(responseSpec.bodyToFlux(Filme.class)).thenReturn(Flux.just(filme1, filme2));

        List<Filme> resultado = filmeService.FiltrobyDiretor(new BuscaRequisicaoDTO("cameron"));

        assertEquals(1, resultado.size());
        assertEquals("James Cameron", resultado.getFirst().getDirector());
    }

    @Test
    void deveRetornarTitulosPorPrefixo() {
        Filme filme1 = new Filme("Titanic","1958","AVENIDA","FIC","tv","James Cameron","bell","bia","tia","123456","654872");
        Filme filme2 = new Filme("Top Gun","1928","AVEA","FICts","tvT","Tony ","belly","bianca","tiara","123456","654872");
        Filme filme3 = new Filme("Avatar","1958","AVENIDA","FIC","tv","James Cameron","bell","bia","tia","123456","654872");


        when(responseSpec.bodyToFlux(Filme.class)).thenReturn(Flux.just(filme1, filme2, filme3));

        List<String> prefixos = filmeService.prefixo(new BuscaRequisicaoDTO("T"));

        assertTrue(prefixos.contains("Titanic"));
        assertTrue(prefixos.contains("Top Gun"));
        assertFalse(prefixos.contains("Avatar"));
    }

}