package com.exemplo.springbootinterview;

import com.exemplo.springbootinterview.cidade.Cidade;
import com.exemplo.springbootinterview.util.Estado;

public class GeradorCidade {

    public static Cidade criaCidadeParaSalvar(){
        return Cidade.builder()
                .nome("Eirunepé")
                .estado(Estado.AM)
                .build();
    }

    public static Cidade criaCidadeValida(){
        return Cidade.builder()
                .nome("Eirunepé")
                .estado(Estado.AM)
                .id(1L)
                .build();
    }

    public static Cidade criaCidadeParaAlterar(){
        return Cidade.builder()
                .nome("Eirunepé")
                .estado(Estado.AM)
                .id(1L)
                .build();
    }
}
