/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.instruccionesParaComando;

import backend.manejoDeDatos.Documento;
import backend.manejoDeDatos.ManejadorDeArboles;
import java.util.ArrayList;

/**
 *
 * @author jesfrin
 */
public class ManejadorLS {

    private final ManejadorDeArboles manejador;

    public ManejadorLS(ManejadorDeArboles manejador) {
        this.manejador = manejador;
    }

    public void buscarHijosDeNodoSinDireccionEspecifica(String direccionDePadre) {//ls 
        ArrayList<String> hijosDeCarpeta = new ArrayList<>();
        for (Documento documento : this.manejador.getDocumentos()) {
            if (documento.getDireccionDePadre().equals(direccionDePadre) && !documento.esOculto()) {
                hijosDeCarpeta.add(documento.getNombre());
            }
        }
        this.manejador.getMiTerminal().mostrarNombresDeDocumentosContenidosEnDirectoirio(hijosDeCarpeta);
    }

    public void buscarY_MostrarInformacionDeHijosSinDireccionEspecifica(String direccionDePadre) {//ls -l
        for (Documento documento : this.manejador.getDocumentos()) {
            if (documento.getDireccionDePadre().equals(direccionDePadre) && !documento.esOculto()) {
                this.manejador.getMiTerminal().mostrarInfoDeDocumentosContenidosEnDirectorio(documento);
            }
        }
    }

    public void buscarY_MostrarInfoDeHijos(String direccionDePadre) {//ls -la|al
        for (Documento documento : this.manejador.getDocumentos()) {
            if (documento.getDireccionDePadre().equals(direccionDePadre)) {
                this.manejador.getMiTerminal().mostrarInfoDeDocumentosContenidosEnDirectorio(documento);
            }
        }
    }

    public void buscarHijosSinDireccionEspecificaOcultos(String direccionDePadre) {//ls -a
        ArrayList<String> hijosDeCarpeta = new ArrayList<>();
        for (Documento documento : this.manejador.getDocumentos()) {
            if (documento.getDireccionDePadre().equals(direccionDePadre)) {
                hijosDeCarpeta.add(documento.getNombre());
            }
        }
        this.manejador.getMiTerminal().mostrarNombresDeDocumentosContenidosEnDirectoirio(hijosDeCarpeta);
    }

    public void buscarHijosConDireccion(String direccion, ManejadorCD manejadorCd, String tipoDeComando) {//Se le manda una direccion echa y derecha
        if (manejadorCd.buscarDireccion(direccion)) {//Si la direccion se ha encontrado se procede , y si no pues ya mandara mismo cd que no existe la direccion
            switch (tipoDeComando) {
                case "ls":
                    buscarHijosDeNodoSinDireccionEspecifica(direccion);
                    break;
                case "ls -l":
                    buscarY_MostrarInformacionDeHijosSinDireccionEspecifica(direccion);
                    break;
                case "ls -a":
                    buscarHijosSinDireccionEspecificaOcultos(direccion);
                    break;
                default :
                    buscarY_MostrarInfoDeHijos(direccion);
                    break;
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
