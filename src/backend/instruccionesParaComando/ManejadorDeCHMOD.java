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
public class ManejadorDeCHMOD {

    private ManejadorDeArboles manejador;

    public ManejadorDeCHMOD(ManejadorDeArboles manejador) {
        this.manejador = manejador;
    }

    public void cambiarPermisosDeArchivo(String direccion, ManejadorCD manejadorCd, String permisos) {//Direccion ya formada
        if (manejadorCd.buscarDireccion(direccion, false)) {//Cd informa si no se ha encontrado la direccion
            Documento doc = null;
            for (Documento documento : manejador.getDocumentos()) {
                if (documento.getDireccion().equals(direccion)) {
                    doc = documento;
                }
            }

            //Cambiar permisos
            doc.cambiarPermisos(permisos);
        }
        
        
    }

}
