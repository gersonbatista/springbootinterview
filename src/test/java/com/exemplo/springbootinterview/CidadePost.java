package com.exemplo.springbootinterview;

import com.exemplo.springbootinterview.cidade.CidadePostDTO;

public class CidadePost {
	public static CidadePostDTO criaPostDTO() {
		return CidadePostDTO.builder().nome("Eirunepé").codigoIbge(13).build();
	}
}
