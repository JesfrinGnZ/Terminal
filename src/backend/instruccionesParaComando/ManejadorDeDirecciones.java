/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.instruccionesParaComando;

import backend.manejoDeDatos.ManejadorDeArboles;

/**
 *
 * @author jesfrin
 */
public class ManejadorDeDirecciones {//Clase que convierte cualquier direccion a una direccion absoluta

    private ManejadorDeArboles manejador;

    public ManejadorDeDirecciones(ManejadorDeArboles manejador) {
        this.manejador = manejador;
    }

    private String restructurarDireccion(String direccion) {//Rewstructura la direccion
        String cadena = "";
        String[] n = direccion.split("/");
        System.out.println(n.length);
        for (String string : n) {
            if (string.length() > 0) {
                cadena += "/" + string;
            }
        }
        return cadena;
    }

    public String manejarDireccionAbsoluta(String direccion) {
        return restructurarDireccion(direccion);
    }

    public String manejarDireccionRelativa(String direccion) {
        direccion = restructurarDireccion(direccion);//Si es raiz no se debe agru
        if (manejador.getMiTerminal().getDireccionActual().equals("/")) {
            System.out.println("DIRECCION RELATIVA FORMADA::"+direccion);
            return direccion;
        } else {
                        System.out.println("DIRECCION RELATIVA FORMADA::"+direccion);

            return manejador.getMiTerminal().getDireccionActual() + direccion;
        }
    }

    public String manejarDireccionId(String direccion) {
        if (manejador.getMiTerminal().getDireccionActual().equals("/")) {
            return manejador.getMiTerminal().getDireccionActual() + direccion;
        } else {
            return manejador.getMiTerminal().getDireccionActual() + "/" + direccion;
        }

    }
    

    public String[] buscarNombreDeArchivo(String direccion){//Debe recibir una direccion ya formada
        String[] direcciones = new String[2];
        String[] direccionesActuales = direccion.split("/");
        String soloDireccion="";
        for (int i = 0; i < direccionesActuales.length-1; i++) {
            soloDireccion+=direccionesActuales[i]+"/";
        }
        direcciones[1]=restructurarDireccion(soloDireccion);
         System.out.println("SOLO DIRECCION:"+direcciones[1]);
        direcciones[0]=direccionesActuales[direccionesActuales.length-1];
                 System.out.println("Nombre de archivo:"+direcciones[0]);

        return direcciones;
    }
}
