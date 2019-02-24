/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.instruccionesParaComando;

import backend.manejoDeDatos.Documento;
import backend.manejoDeDatos.ManejadorDeArboles;

/**
 *
 * @author jesfrin
 */
public class ManejadorCD {

    private final ManejadorDeArboles manejador;

    public ManejadorCD(ManejadorDeArboles manejador) {
        this.manejador = manejador;
    }

    public void buscarDireccion(String direccion) {
        if (direccion == null) {
            this.manejador.getMiTerminal().cambiarDireccionEnTerminal(this.manejador.getDocumentos().get(0));
        } else {
            boolean seEncontroElArchivo = false;
            for (Documento documento : manejador.getDocumentos()) {
                if (documento.getDireccion().equals(direccion) && documento.esFolder()) {
                    manejador.getMiTerminal().cambiarDireccionEnTerminal(documento);
                    seEncontroElArchivo = true;
                    break;
                }
            }
            if (!seEncontroElArchivo) {
                manejador.getMiTerminal().informarQueNoSeHEncontradoElDirectorio();
            }
        }

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
            return direccion;
        } else {
            return manejador.getMiTerminal().getDireccionActual() + direccion;
        }
    }

    public String manejarDireccionId(String direccion) {
        System.out.println("DIRECCION:::" + manejador.getMiTerminal().getDireccionActual() + "/" + direccion);
        if (manejador.getMiTerminal().getDireccionActual().equals("/")) {
            return manejador.getMiTerminal().getDireccionActual() + direccion;
        } else {
            return manejador.getMiTerminal().getDireccionActual() + "/" + direccion;
        }

    }

}
