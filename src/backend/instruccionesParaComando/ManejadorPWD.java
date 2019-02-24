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
public class ManejadorPWD {

    private ManejadorDeArboles manejador;

    public ManejadorPWD(ManejadorDeArboles manejador) {
        this.manejador = manejador;
    }

    public void escribirDireccion() {
        this.manejador.getMiTerminal().anadirTextoATerminal(this.manejador.getMiTerminal().getDireccionActual());
    }

}
