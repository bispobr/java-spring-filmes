package com.example.filme.controller;

import com.example.filme.dto.BuscaRequisicaoDTO;
import com.example.filme.dto.BuscaRespostaDTO;
import com.example.filme.dto.FilmeRespostaDTO;
import com.example.filme.mapper.FilmeMapper;
import com.example.filme.model.Filme;
import com.example.filme.service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmeControllerTest {

    @Mock
    private FilmeService filmeService;

    @Mock
    FilmeMapper mapper;

    @InjectMocks
    private  FilmeController controller;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void deveListarTodosOsFilmes() {
        Filme filme = new Filme("Titanic","1958","AVENIDA","FIC","tv","director","bell","bia","tia","123456","654872");
        FilmeRespostaDTO respostaDTO = new FilmeRespostaDTO("Titanic","1958","AVENIDA","FIC","tv","director","bell","bia","tia","123456","654872");

        when(filmeService.pegarTodosFilmes()).thenReturn(List.of(filme));
        when(mapper.paraRespostaList(List.of(filme))).thenReturn(List.of(respostaDTO));

        ResponseEntity<List<FilmeRespostaDTO>> resposta = controller.listarfilmes();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(1, resposta.getBody().size());
        assertEquals("Titanic", resposta.getBody().getFirst().title());
    }

    @Test
    void deveRetornarFilmesPorTitulo() {
        BuscaRequisicaoDTO dto = new BuscaRequisicaoDTO("Titanic");
        Filme filme = new Filme("Titanic","1958","AVENIDA","FIC","tv","director","bell","bia","tia","123456","654872");
        FilmeRespostaDTO respostaDTO = new FilmeRespostaDTO("Titanic","1958","AVENIDA","FIC","tv","director","bell","bia","tia","123456","654872");

        when(filmeService.FiltrobyTitulo(dto)).thenReturn(List.of(filme));
        when(mapper.paraRespostaList(List.of(filme))).thenReturn(List.of(respostaDTO));

        ResponseEntity<Object> resposta = controller.listarfilmesTitulo(dto);

        assertEquals(200, resposta.getStatusCodeValue());
        assertTrue(resposta.getBody() instanceof List);
    }

    @Test
    void deveRetornarNotFoundQuandoNenhumFilmeEncontradoPorTitulo() {
        BuscaRequisicaoDTO dto = new BuscaRequisicaoDTO("Inexistente");

        when(filmeService.FiltrobyTitulo(dto)).thenReturn(List.of());

        ResponseEntity<Object> resposta = controller.listarfilmesTitulo(dto);

        assertEquals(404, resposta.getStatusCodeValue());
    }

    @Test
    void deveBuscarTitulosPorPrefixo() {
        BuscaRequisicaoDTO dto = new BuscaRequisicaoDTO("Ti");
        List<String> titulos = List.of("Titanic");
        List<BuscaRespostaDTO> respostaDTO = List.of(new BuscaRespostaDTO("Titanic"));

        when(filmeService.prefixo(dto)).thenReturn(titulos);
        when(mapper.paraRespostaBuscaList(titulos)).thenReturn(respostaDTO);

        ResponseEntity<List<BuscaRespostaDTO>> resposta = controller.prefixo(dto);

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(1, resposta.getBody().size());
        assertEquals("Titanic", resposta.getBody().get(0).Resultado());
    }

    @Test
    void deveRetornarNotFoundSeNenhumPrefixoEncontrado() {
        BuscaRequisicaoDTO dto = new BuscaRequisicaoDTO("xyz");

        when(filmeService.prefixo(dto)).thenReturn(List.of());

        ResponseEntity<List<BuscaRespostaDTO>> resposta = controller.prefixo(dto);

        assertEquals(404, resposta.getStatusCodeValue());
    }

    @Test
    void deveListarFilmesPorDiretor() {
        BuscaRequisicaoDTO dto = new BuscaRequisicaoDTO("Cameron");
        Filme filme = new Filme("Titanic","1958","AVENIDA","FIC","tv","James Cameron","bell","bia","tia","123456","654872");

        FilmeRespostaDTO respostaDTO = new FilmeRespostaDTO("Titanic","1958","AVENIDA","FIC","tv","James Cameron","bell","bia","tia","123456","654872");


        when(filmeService.FiltrobyDiretor(dto)).thenReturn(List.of(filme));
        when(mapper.paraRespostaList(List.of(filme))).thenReturn(List.of(respostaDTO));

        ResponseEntity<List<FilmeRespostaDTO>> resposta = controller.listarfilmesDiretor(dto);

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals("Titanic", resposta.getBody().get(0).title());
    }

    @Test
    void deveRetornarNotFoundSeDiretorNaoForEncontrado() {
        BuscaRequisicaoDTO dto = new BuscaRequisicaoDTO("Desconhecido");

        when(filmeService.FiltrobyDiretor(dto)).thenReturn(List.of());

        ResponseEntity<List<FilmeRespostaDTO>> resposta = controller.listarfilmesDiretor(dto);

        assertEquals(404, resposta.getStatusCodeValue());
    }

}