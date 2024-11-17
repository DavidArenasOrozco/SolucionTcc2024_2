package com.example.BODEGASTCCAPI.modelos.mapas;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaMercancia {

    @Mappings({
            @Mapping(source = "volumen", target = "volumen"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "fechaIngreso", target = "fechaIngreso"),
    })
    MercanciaDTO mapearMercancia(Mercancia mercancia);

    List<MercanciaDTO> mapearListaMercancias(List<Mercancia> listaMercancia);

}
