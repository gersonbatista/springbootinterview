package com.exemplo.springbootinterview;

import com.exemplo.springbootinterview.cidade.CidadePutDTO;
import com.exemplo.springbootinterview.util.Estado;

public class CidadePut {
	public static CidadePutDTO criaPutDTO() {
		return CidadePutDTO.builder().id(1l).nome("Eirunepé").codigoIbge(13).build();
	}
}
