package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.servicios.ZonaBodegaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/soluciontcc/v1/zonas")
public class ControladorZonaBodega {

    @Autowired
    ZonaBodegaServicio zonaBodegaServicio;


    @PostMapping
    public ResponseEntity<ZonaBodega> guardarZonaBodega(@RequestBody ZonaBodega zona) throws Exception {

        try {
            return ResponseEntity.ok(zonaBodegaServicio.almacenarZonaBodega(zona));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ZonaBodega>> obtenerTodasZonasBodega() {
        try {
            return ResponseEntity.ok(zonaBodegaServicio.buscarTodasZonasBodega());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Reduce the warehouse capacity in a specific zone")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Warehouse capacity reduced successfully"), @ApiResponse(responseCode = "500", description = "Error reducing warehouse capacity")})
    @PutMapping("{idZona}/restar")
    public ResponseEntity<?> restarCapacidadBodega(@PathVariable long idZona, @RequestParam double volumenMercancia, @RequestParam double pesoMercancia) throws Exception {
        try {
            return ResponseEntity.ok(this.zonaBodegaServicio.restarCapacidadBodega(idZona, volumenMercancia, pesoMercancia));
        } catch (Exception error) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);

        }

    }
}