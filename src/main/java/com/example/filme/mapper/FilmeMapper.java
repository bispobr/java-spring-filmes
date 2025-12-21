package com.example.filme.mapper;

import com.example.filme.dto.BuscaRespostaDTO;
import com.example.filme.dto.FilmeRespostaDTO;
import com.example.filme.model.Filme;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmeMapper {

    public List<FilmeRespostaDTO> paraRespostaList(List<Filme> lista){
        return lista.stream().map(l->{
            return new FilmeRespostaDTO(l.getTitle(), l.getRelease_year(), l.getLocations(),l.getProduction_company(),
                    l.getDistributor(),l.getDirector(),l.getActor_1(),l.getActor_2(),l.getActor_3(),l.getLongitude(),
                    l.getLatitude());
        }).toList();
    }

    public List<BuscaRespostaDTO> paraRespostaBuscaList(List<String> lista){
        return lista.stream().map(BuscaRespostaDTO::new).toList();
    }
}
