/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.instruccionesParaComando;

import backend.manejoDeDatos.Documento;
import backend.manejoDeDatos.ManejadorDeArboles;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author jesfrin
 */
public class ManejadorLS {

    private final ManejadorDeArboles manejador;

    public ManejadorLS(ManejadorDeArboles manejador) {
        this.manejador = manejador;
    }

    public void buscarHijosDeNodoSinDireccionEspecifica() {//ls 
        DefaultMutableTreeNode nodo = null;
        ArrayList<String> hijosDeCarpeta = new ArrayList<>();
        for (Documento documento : this.manejador.getDocumentos()) {
            if (documento.getDireccion().equals(this.manejador.getMiTerminal().getDireccionActual())) {
                nodo = documento.getNodo();
                break;
            }
        }
        DefaultTreeModel modelo = new DefaultTreeModel(nodo);
        int numeroDeHijos = modelo.getChildCount(nodo);
        for (int i = 0; i < numeroDeHijos; i++) {
            DefaultMutableTreeNode nodoHijo = (DefaultMutableTreeNode) modelo.getChild(nodo, i);
            hijosDeCarpeta.add(nodoHijo.toString());
        }
        this.manejador.getMiTerminal().mostrarNombresDeDocumentosContenidosEnDirectoirio(hijosDeCarpeta);
    }

    public void buscarY_MostrarInformacionDeHijosSinDireccionEspecifica() {//ls -l
        String direccionDePadre = this.manejador.getMiTerminal().getDireccionActual();
        for (Documento documento : this.manejador.getDocumentos()) {
            if(documento.getDireccionDePadre().equals(direccionDePadre)){
                this.manejador.getMiTerminal().mostrarInfoDeDocumentosContenidosEnDirectorio(documento);
            }
        }
    }

//    public void buscarHijosDeNodo(String direccion) {
//        ArrayList<String> hijosDeCarpeta = new ArrayList<>();
//        DefaultMutableTreeNode nodo;
//        if (direccion == null) {
//            nodo = buscarNodo(this.miTerminal.getDireccionActual());
//        } else {
//            direccion = restructurarDireccion(direccion);
//            nodo = buscarNodo(direccion);
//        }
//        DefaultTreeModel modelo = new DefaultTreeModel(nodo);
//        int numeroDeHijos = modelo.getChildCount(nodo);
//        for (int i = 0; i < numeroDeHijos; i++) {
//            DefaultMutableTreeNode nodoHijo = (DefaultMutableTreeNode) modelo.getChild(nodo, i);
//            hijosDeCarpeta.add(nodoHijo.toString());
//        }
//        this.miTerminal.mostrarNombresDeDocumentosContenidosEnDirectoirio(hijosDeCarpeta);
//    }
//
//    private DefaultMutableTreeNode buscarNodo(String direccion) {
//        for (Documento documento : documentos) {
//            if (documento.getDireccion().equals(direccion)) {
//                return documento.getNodo();
//            }
//        }
//        return null;
//    }
}
