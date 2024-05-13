/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bd;

import java.time.LocalDate;

/**
 *
 * @author Franco
 */
public class clientes {

    int id;
    String nombre, apellidos, telefono, telemergencias;
    LocalDate fechanacimiento, fechainicio, membresia_hasta;

    public clientes(int id, String nombre, String apellidos, LocalDate fechanacimiento, String telefono, String telemergencias, LocalDate fechainicio, LocalDate membresia_hasta) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.telefono = telefono;
        this.telemergencias = telemergencias;
        this.fechainicio = fechainicio;
        this.membresia_hasta = membresia_hasta;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelemergencias() {
        return telemergencias;
    }

    public void setTelemergencias(String telemergencias) {
        this.telemergencias = telemergencias;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public LocalDate getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(LocalDate fechainicio) {
        this.fechainicio = fechainicio;
    }

    public LocalDate getMembresia_hasta() {
        return membresia_hasta;
    }

    public void setMembresia_hasta(LocalDate membresia_hasta) {
        this.membresia_hasta = membresia_hasta;
    }

    
 
}
