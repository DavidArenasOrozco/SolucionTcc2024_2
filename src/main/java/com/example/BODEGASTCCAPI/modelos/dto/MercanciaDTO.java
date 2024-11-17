package com.example.BODEGASTCCAPI.modelos.dto;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;

import java.time.LocalDate;

public class MercanciaDTO {
    private Double volumen;
    private Double peso;
    private String nombre;
    private String direccion;
    private LocalDate fechaIngreso;

    private ZonaBodega zonaBodega;

    public MercanciaDTO() {
    }

    public MercanciaDTO(Double volumen, Double peso, String nombre, String direccion, LocalDate fechaIngreso, ZonaBodega zonaBodega) {
        this.volumen = volumen;
        this.peso = peso;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaIngreso = fechaIngreso;
        this.zonaBodega = zonaBodega;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public ZonaBodega getZonaBodega() {
        return zonaBodega;
    }

    public void setZonaBodega(ZonaBodega zonaBodega) {
        this.zonaBodega = zonaBodega;
    }
}


