/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.manejoDeDatos;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author jesfrin
 */
public class Documento {

    private String direccion, direccionDePadre, nombre, permisos;
    private boolean esFolder;
    private int tamano;
    private LocalDate fechaDeCreacion;
    private LocalTime horaDeCreacion;
    private DefaultMutableTreeNode nodo;

    public Documento(String direccion, String direccionDePadre, String nombre, String permisos, boolean esFolder, int tamano, LocalDate fechaDeCreacion, DefaultMutableTreeNode nodo,LocalTime horaDeCreacion) {
        this.direccion = direccion;
        this.direccionDePadre = direccionDePadre;
        this.nombre = nombre;
        this.permisos = permisos;
        this.esFolder = esFolder;
        this.tamano = tamano;
        this.fechaDeCreacion = fechaDeCreacion;
        this.nodo = nodo;
        this.horaDeCreacion= horaDeCreacion;
    }

    public Documento(String direccion) {
        this.direccion = direccion;
    }

    public Documento() {

    }

    //Getter and Setter
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccionDePadre() {
        return direccionDePadre;
    }

    public void setDireccionDePadre(String direccionDePadre) {
        this.direccionDePadre = direccionDePadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public boolean esFolder() {
        return esFolder;
    }

    public void setEsFolder(boolean esFolder) {
        this.esFolder = esFolder;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }


    public void setFechaDeCreacion(String fechaDeCreacion) {
        String[] datosDeFecha = fechaDeCreacion.split("/");
        int dia,mes,anio;
        dia=Integer.valueOf(datosDeFecha[0]);
        mes=Integer.valueOf(datosDeFecha[1]);
        anio=Integer.valueOf(datosDeFecha[2]);
        this.fechaDeCreacion = LocalDate.of(anio, mes, dia);
        }

    public DefaultMutableTreeNode getNodo() {
        return nodo;
    }

    public void setNodo(DefaultMutableTreeNode nodo) {
        this.nodo = nodo;
    }

    public LocalTime getHoraDeCreacion() {
        return horaDeCreacion;
    }

    public void setHoraDeCreacion(String horaDeCreacion) {
        String[] hora = horaDeCreacion.split(":");
        this.horaDeCreacion=LocalTime.of(Integer.valueOf(hora[0]), Integer.valueOf(hora[1]));
    }

}
