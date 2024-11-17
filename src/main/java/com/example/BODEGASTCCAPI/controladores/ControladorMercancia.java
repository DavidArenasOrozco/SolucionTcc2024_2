package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.servicios.MercanciaServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/soluciontcc/v1/mercancias")
@Tag(name="Servicios asociados a la entidad o tabla mercancias", description = "\nse hace CRUD completo a la tabla mercancia permitiendo lectura y escritura de datos")
public class ControladorMercancia {

    //Inyectar una dependencia al servicio
    @Autowired
    MercanciaServicio mercanciaServicio;

    //llamar a cada uno de los metodos disponibles
    // en el servicio

    @PostMapping()
    @Operation(
            summary = "Registra una mercancia nueva en la base de datos",
            description = "al llevar los datos del modelo mercancia se permite un registro exitoso del objeto en BD"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Mercancia almecenada con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MercanciaDTO.class),
                                    examples = @ExampleObject(value = "{\"volumen\":\"20.5\",\"peso\":\"400\",\"nombre\":\"Nevera LG\",\"direccion\":\"calle 100 sur 123\",\"fechaIngreso\":\"2024-10-8\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al registrar la mercancia",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"eL volumen no puede ser negativo\"}")
                            )
                    )
            }
    )
    public ResponseEntity<Boolean> LlamadoGuardarMercanciaDTO(@RequestBody Mercancia datosMercanciaEnviadosCliente) {
        try {
            // Llama al servicio para almacenar la mercancía y devuelve true si se almacena correctamente
            boolean resultado = this.mercanciaServicio.almacenarMercanciaDTO(datosMercanciaEnviadosCliente);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resultado);
        } catch (Exception error) {
            // En caso de error, devuelve false indicando que no se pudo almacenar la mercancía
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(false);
        }
    }



    @GetMapping()
    @Operation(
            summary = "Buscar todas lar emrcancias almacenadas en BD",
            description = "Se encuentran todos los registros y se envian en formato JSON hacia el cliente"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Mercancias encontradas con exito en BD",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MercanciaDTO.class),
                                    examples = @ExampleObject(value = "{\"volumen\":\"20.5\",\"peso\":\"400\",\"nombre\":\"Nevera LG\",\"direccion\":\"calle 100 sur 123\",\"fechaIngreso\":\"2024-10-8\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al buscar la mercancias",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"eL volumen no puede ser negativo\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> LlamadoBuscarMercanciasDTO(){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.mercanciaServicio.buscarTodasMercancias());
        }catch(Exception error){
            HashMap<String, Object> mensajeRespuesta= new HashMap<>();
            mensajeRespuesta.put("mensaje",error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);
        }

    }



}
