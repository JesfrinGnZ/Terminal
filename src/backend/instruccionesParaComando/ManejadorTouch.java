/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.instruccionesParaComando;

import backend.manejoDeDatos.Documento;
import backend.manejoDeDatos.ManejadorDeArboles;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author jesfrin
 */
public class ManejadorTouch {

    private final ManejadorDeArboles manejador;

    public ManejadorTouch(ManejadorDeArboles manejador) {
        this.manejador = manejador;
    }

    public void crearArchivos(String nombreDeHijo) {
        String direccionActual = this.manejador.getMiTerminal().getDireccionActual();
        DefaultMutableTreeNode nuevoNodo = new DefaultMutableTreeNode(nombreDeHijo);
        DefaultMutableTreeNode nodo= null;
        for (Documento documento : this.manejador.getDocumentos()) {
            if(documento.getDireccion().equals(direccionActual)){
                nodo = documento.getNodo();
                break;
            }
        }
        nodo.add(nuevoNodo);
        this.manejador.getMiTerminal().actualizarArbol();
        Documento nuevoDoc = new Documento(direccionActual+"/"+nombreDeHijo,direccionActual, nombreDeHijo, "-rwx", false, 0, LocalDate.now(), nuevoNodo,LocalTime.now());
        this.manejador.anadirDocumentoA_Lista(nuevoDoc);
    }

}
