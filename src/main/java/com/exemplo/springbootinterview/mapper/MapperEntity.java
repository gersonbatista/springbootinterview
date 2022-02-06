package com.exemplo.springbootinterview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.exemplo.springbootinterview.cidade.Cidade;
import com.exemplo.springbootinterview.cidade.CidadePostDTO;
import com.exemplo.springbootinterview.cidade.CidadePutDTO;
import com.exemplo.springbootinterview.cliente.Cliente;
import com.exemplo.springbootinterview.cliente.ClientePostDTO;
import com.exemplo.springbootinterview.cliente.ClientePutDTO;

@Mapper(componentModel = "spring")
public abstract class MapperEntity {
    public static final MapperEntity INSTANCE = Mappers.getMapper(MapperEntity.class);

    @Mapping(source = "estado", target = "estado")
    public abstract Cidade toCidade(CidadePostDTO dto);
    
    public abstract Cidade toCidade(CidadePutDTO dto);
    
    @Mapping(target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    public abstract Cliente toCliente(ClientePostDTO dto);
    
    public abstract Cliente toCliente(ClientePutDTO dto);

}