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

    public boolean buscarDireccion(String direccion, boolean seDebeCambiarDireccion) {
        if (direccion == null) {//Vuelva a raiz
            this.manejador.getMiTerminal().cambiarDireccionEnTerminal(this.manejador.getDocumentos().get(0));
            return true;
        } else {//Busque la direccion que se le ha especificado
            for (Documento documento : manejador.getDocumentos()) {
                if (documento.getDireccion().equals(direccion) && documento.esFolder()) {
                    if (seDebeCambiarDireccion) {
                        manejador.getMiTerminal().cambiarDireccionEnTerminal(documento);
                    }
                    return true;

                }
            }
            manejador.getMiTerminal().informarQueNoSeHEncontradoElDirectorio();
            return false;
        }

    }

}
