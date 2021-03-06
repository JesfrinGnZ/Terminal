/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.manejoDeDatos;

import gui.Frame.TerminalGui;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author jesfrin
 */
public class ManejadorDeArboles {

    private final TerminalGui miTerminal;
    public DefaultMutableTreeNode raiz;
    public ArrayList<Documento> documentos;

    public ManejadorDeArboles(TerminalGui miTerminal) {
        this.miTerminal = miTerminal;
        this.raiz = this.miTerminal.llamarRaizDeArbol();
        this.documentos = new ArrayList<>();
        crearDocumentoRaiz();
        
    }

    private void crearDocumentoRaiz() {
        String direccion = "/";
        String direccionDePadre = "@";
        String nombre = "/";
        String permisos = "drwx";
        boolean esFolder = true;
        int tamano = 0;
        LocalDate fechaDeCreacion = LocalDate.of(2019, 2, 17);
        DefaultMutableTreeNode nodoRaiz = raiz;
        Documento nuevoDoc = new Documento(direccion, direccionDePadre, nombre, permisos, esFolder, tamano, fechaDeCreacion, nodoRaiz,LocalTime.now(),false);
        documentos.add(nuevoDoc);
    }

    public void anadirDocumentoA_Lista(Documento documento) {
        documentos.add(documento);
    }

    public void mostrarInformacionDeDocumentos() {
        System.out.println("------------------------------Valores------------------------------\n");
        for (Documento documento : documentos) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Direccion:" + documento.getDireccion());
            System.out.println("Direccion de padre:" + documento.getDireccionDePadre());
            System.out.println("Nombre:" + documento.getNombre());
            System.out.println("Permisos:" + documento.getPermisos());
            System.out.println("Es folder:" + documento.esFolder());
            System.out.println("Tamano:" + documento.getTamano());
            System.out.println("FechaDeCreacion" + documento.getFechaDeCreacion());
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    public void anadirNodoA_Arbol(String direccionDePadre, DefaultMutableTreeNode nodo) {
        if (direccionDePadre.equals("/")) {
            raiz.add(nodo);
        } else {
            for (Documento documento : documentos) {//Buscar el padre del documento actual para anadirlo
                if (documento.getDireccion().equals(direccionDePadre)) {
                    documento.getNodo().add(nodo);
                    break;
                }
            }
        }

    }

    //-------------------------->Metodos auxiliares--------------------------
    private String restructurarDireccion(String direccion) {
        if (!direccion.startsWith("/")) {//Comparacion si es direccion relativa
            if (this.miTerminal.getDireccionActual().equals("/")) {
                direccion = this.miTerminal.getDireccionActual() + direccion;//Solo se anade la direccion /direccion
            } else {
                direccion = this.miTerminal.getDireccionActual() + "/" + direccion;//Se anade de la forma /direccionActual/direccion
            }
        }

        if (direccion.endsWith("/")) {//Comparacion si es direccion que termina con diagonal, y se elimina
            direccion = direccion.substring(0, direccion.length() - 1);
        }
        return direccion;
    }

    //------------------------------>LS<-------------------------------------------
    private DefaultMutableTreeNode buscarNodo(String direccion) {
        for (Documento documento : documentos) {
            if (documento.getDireccion().equals(direccion)) {
                return documento.getNodo();
            }
        }
        return null;
    }
    //ls solo

    public void buscarHijosDeNodo(String direccion) {
        ArrayList<String> hijosDeCarpeta = new ArrayList<>();
        DefaultMutableTreeNode nodo;
        if (direccion == null) {
            nodo = buscarNodo(this.miTerminal.getDireccionActual());
        } else {
            direccion = restructurarDireccion(direccion);
            nodo = buscarNodo(direccion);
        }
        DefaultTreeModel modelo = new DefaultTreeModel(nodo);
        int numeroDeHijos = modelo.getChildCount(nodo);
        for (int i = 0; i < numeroDeHijos; i++) {
            DefaultMutableTreeNode nodoHijo = (DefaultMutableTreeNode) modelo.getChild(nodo, i);
            hijosDeCarpeta.add(nodoHijo.toString());
        }
        this.miTerminal.mostrarNombresDeDocumentosContenidosEnDirectoirio(hijosDeCarpeta);
    }

    public DefaultMutableTreeNode getRaiz() {
        return raiz;
    }

    public void setRaiz(DefaultMutableTreeNode raiz) {
        this.raiz = raiz;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(ArrayList<Documento> documentos) {
        this.documentos = documentos;
    }

    public TerminalGui getMiTerminal() {
        return miTerminal;
    }
    
    
}
