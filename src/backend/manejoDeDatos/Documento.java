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
    private boolean esOculto;
    private boolean tienePermisoDeLectura;
    private boolean tienePermisoDeEscritura;
    private boolean tienePermisoDeEjecucion;

    public Documento(String direccion, String direccionDePadre, String nombre, String permisos, boolean esFolder, int tamano, LocalDate fechaDeCreacion, DefaultMutableTreeNode nodo, LocalTime horaDeCreacion, boolean esOculto) {
        this.direccion = direccion;
        this.direccionDePadre = direccionDePadre;
        this.nombre = nombre;
        this.permisos = permisos;
        this.esFolder = esFolder;
        this.tamano = tamano;
        this.fechaDeCreacion = fechaDeCreacion;
        this.nodo = nodo;
        this.horaDeCreacion = horaDeCreacion;
        this.esOculto = esOculto;
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
        this.tienePermisoDeLectura = permisos.charAt(1) != '-';
        this.tienePermisoDeEscritura = permisos.charAt(2) != '-';
        this.tienePermisoDeEjecucion = permisos.charAt(3) != '-';
    }

    public void cambiarPermisos(String permiso) {
        boolean seDebeAnadir = false;
        if (permiso.startsWith("+")) {
            seDebeAnadir = true;
        }
        StringBuilder cadena = new StringBuilder(permisos);
        String permisoTemp = "";
        for (int i = 1; i < permiso.length(); i++) {
            permisoTemp += permiso.charAt(i);

        }

        switch (permisoTemp) {
            case "r":
                if (seDebeAnadir) {
                    cadena.setCharAt(1, 'r');
                } else {
                    cadena.setCharAt(1, '-');
                }
                break;
            case "w":
                if (seDebeAnadir) {
                    cadena.setCharAt(2, 'w');
                } else {
                    cadena.setCharAt(2, '-');
                }
                break;
            case "x":
                if (seDebeAnadir) {
                    cadena.setCharAt(3, 'x');
                } else {
                    cadena.setCharAt(3, '-');
                }
                break;
            case "rw":
                if (seDebeAnadir) {
                    cadena.setCharAt(1, 'r');
                    cadena.setCharAt(2, 'w');
                } else {
                    cadena.setCharAt(1, '-');
                    cadena.setCharAt(2, '-');
                }
                break;
            case "rx":
                if (seDebeAnadir) {
                    cadena.setCharAt(1, 'r');
                    cadena.setCharAt(3, 'x');
                } else {
                    cadena.setCharAt(1, '-');
                    cadena.setCharAt(3, '-');
                }
                break;
            case "wr":
                if (seDebeAnadir) {
                    cadena.setCharAt(2, 'w');
                    cadena.setCharAt(1, 'r');
                } else {
                    cadena.setCharAt(2, '-');
                    cadena.setCharAt(1, '-');
                }
                break;
            case "wx":
                if (seDebeAnadir) {
                    cadena.setCharAt(2, 'w');
                    cadena.setCharAt(3, 'x');
                } else {
                    cadena.setCharAt(2, '-');
                    cadena.setCharAt(3, '-');
                }
                break;
            case "xr":
                if (seDebeAnadir) {
                    cadena.setCharAt(3, 'x');
                    cadena.setCharAt(1, 'r');
                } else {
                    cadena.setCharAt(3, '-');
                    cadena.setCharAt(1, '-');
                }
                break;
            case "xw":
                if (seDebeAnadir) {
                    cadena.setCharAt(3, 'x');
                    cadena.setCharAt(2, 'w');
                } else {
                    cadena.setCharAt(3, '-');
                    cadena.setCharAt(2, '-');
                }
                break;
            default:
                if (seDebeAnadir) {
                    cadena.setCharAt(1, 'r');
                    cadena.setCharAt(2, 'w');
                    cadena.setCharAt(3, 'x');
                } else {
                    cadena.setCharAt(1, '-');
                    cadena.setCharAt(2, '-');
                    cadena.setCharAt(3, '-');
                }
                break;

        }
        permisos = cadena.toString();

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
        int dia, mes, anio;
        dia = Integer.valueOf(datosDeFecha[0]);
        mes = Integer.valueOf(datosDeFecha[1]);
        anio = Integer.valueOf(datosDeFecha[2]);
        this.fechaDeCreacion = LocalDate.of(anio, mes, dia);
    }

    public void setFechaDeCreacionConFechaDeSistema() {
        this.fechaDeCreacion = LocalDate.now();
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
        this.horaDeCreacion = LocalTime.of(Integer.valueOf(hora[0]), Integer.valueOf(hora[1]));
    }

    public void setHoraDeCreacionConHoraDelSistema() {
        this.horaDeCreacion = LocalTime.now();
    }

    public boolean esOculto() {
        return esOculto;
    }

    public void setEsOculto(boolean esOculto) {
        this.esOculto = esOculto;
    }

    public boolean isTienePermisoDeLectura() {
        return tienePermisoDeLectura;
    }

    public void setTienePermisoDeLectura(boolean tienePermisoDeLectura) {
        this.tienePermisoDeLectura = tienePermisoDeLectura;
    }

    public boolean isTienePermisoDeEscritura() {
        return tienePermisoDeEscritura;
    }

    public void setTienePermisoDeEscritura(boolean tienePermisoDeEscritura) {
        this.tienePermisoDeEscritura = tienePermisoDeEscritura;
    }

    public boolean isTienePermisoDeEjecucion() {
        return tienePermisoDeEjecucion;
    }

    public void setTienePermisoDeEjecucion(boolean tienePermisoDeEjecucion) {
        this.tienePermisoDeEjecucion = tienePermisoDeEjecucion;
    }

}
