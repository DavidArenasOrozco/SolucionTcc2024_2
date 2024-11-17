package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.repositorios.IZonaBodegaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ZonaBodegaServicio {

    @Autowired
    IZonaBodegaRepositorio repositorio;

    public ZonaBodega almacenarZonaBodega(ZonaBodega zona) throws Exception {


        try {
            return repositorio.save(zona);

        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }

    }

    public List<ZonaBodega> buscarTodasZonasBodega() throws Exception {

        try {
            return repositorio.findAll();

        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }

    }


    public ZonaBodega restarCapacidadBodega(Long idBodega, double volumenMercancia, double pesoMercancia) throws Exception {
        try {


            ZonaBodega bodega = repositorio.findById(idBodega)
                    .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));



            if (bodega.getCapacidadPesoOcupado() >= pesoMercancia && bodega.getCapacidadVolumenOcupado() >= volumenMercancia){

                bodega.setCapacidadPesoOcupado(bodega.getCapacidadPesoOcupado() - pesoMercancia);
                bodega.setCapacidadVolumenOcupado((bodega.getCapacidadVolumenOcupado() - volumenMercancia));
            }

            return repositorio.save(bodega);

        } catch (Exception error) {
            throw new Exception(Mensaje.CAPACIDAD_MAXIMA.getMensaje());
        }
    }
}



