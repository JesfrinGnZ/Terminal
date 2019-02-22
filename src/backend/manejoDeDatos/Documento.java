/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.manejoDeDatos;

import java.util.Date;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 *
 * @author jesfrin
 */
public class Documento {
    
    private String direccion, direccionDePadre, nombre,permisos,horaDeCreacion;
    private boolean esFolder;
    private int tamano;
    private Date fechaDeCreacion;
    private DefaultMutableTreeNode nodo;

    public Documento(String direccion, String direccionDePadre, String nombre, String permisos, boolean esFolder, int tamano, Date fechaDeCreacion, DefaultMutableTreeNode nodo) {
        this.direccion = direccion;
        this.direccionDePadre = direccionDePadre;
        this.nombre = nombre;
        this.permisos = permisos;
        this.esFolder = esFolder;
        this.tamano = tamano;
        this.fechaDeCreacion = fechaDeCreacion;
        this.nodo = nodo;
    }

    public Documento(String direccion) {
        this.direccion = direccion;
    }
    
    public Documento(){
        
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

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(String fechaDeCreacion) {
        String[] datosDeFecha = fechaDeCreacion.split("/");
        int dia,mes,anio;
        dia=Integer.valueOf(datosDeFecha[0]);
        mes=Integer.valueOf(datosDeFecha[1]);
        anio=Integer.valueOf(datosDeFecha[2]);
        this.fechaDeCreacion = new Date(anio, mes, dia);
    }

    public DefaultMutableTreeNode getNodo() {
        return nodo;
    }

    public void setNodo(DefaultMutableTreeNode nodo) {
        this.nodo = nodo;
    }

    public String getHoraDeCreacion() {
        return horaDeCreacion;
    }

    public void setHoraDeCreacion(String horaDeCreacion) {
        this.horaDeCreacion = horaDeCreacion;
    }
    
    
    
}
