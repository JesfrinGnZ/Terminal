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
public class ManejadorMKDIR {

    private final ManejadorDeArboles manejador;

    public ManejadorMKDIR(ManejadorDeArboles manejador) {
        this.manejador = manejador;
    }

    public boolean buscarDirectorio(String nombreDeDirectorio) {
        String direccionActual = this.manejador.getMiTerminal().getDireccionActual();
        for (Documento documento : this.manejador.getDocumentos()) {
            if (documento.getNombre().equals(nombreDeDirectorio) && documento.getDireccionDePadre().equals(direccionActual)) {
                return true;
            }
        }
        return false;
    }

    private void crearCarpetasDeDireccion(String direccionDePadre, DefaultMutableTreeNode nodoPadre) {
        DefaultMutableTreeNode nodo;
        for (int i = 1; i < 3; i++) {
            Documento nuevoDoc;
            if (i == 1) {
                nodo = new DefaultMutableTreeNode(".");
                nuevoDoc = new Documento(direccionDePadre + "/.", direccionDePadre, ".", "-rwx", false, 1, LocalDate.now(), nodo, LocalTime.now(), true);
                this.manejador.getDocumentos().add(nuevoDoc);
                nodoPadre.add(nodo);
            } else {
                nodo = new DefaultMutableTreeNode("..");
                nuevoDoc = new Documento(direccionDePadre + "/..", direccionDePadre, "..", "-rwx", false, 1, LocalDate.now(), nodo, LocalTime.now(), true);
                this.manejador.getDocumentos().add(nuevoDoc);
                nodoPadre.add(nodo);
            }
        }
    }

    public void crearDirectorioSoloConId(String nombreDeHijo,String direccionP) {
        String direccionActual = direccionP;
        DefaultMutableTreeNode nuevoNodo = new DefaultMutableTreeNode(nombreDeHijo);//Se crea el nodo
        if (buscarDirectorio(nombreDeHijo)) {
            this.manejador.getMiTerminal().informarQueLaCarpetaYaExiste(nombreDeHijo);
        } else {//Se busca el nodo actual
            DefaultMutableTreeNode nodo = null;
            for (Documento documento : this.manejador.getDocumentos()) {
                if (documento.getDireccion().equals(direccionActual)) {
                    nodo = documento.getNodo();
                    break;
                }
            }
            nodo.add(nuevoNodo);//Se anade la carpeta a l nodo
            String direccion;
            if (direccionActual.equals("/")) {//Se crea el objeto documento
                direccion = direccionActual + nombreDeHijo;
            } else {
                direccion = direccionActual + "/" + nombreDeHijo;
            }
            Documento nuevoDoc = new Documento(direccion, direccionActual, nombreDeHijo, "-rwx", true, 1, LocalDate.now(), nuevoNodo, LocalTime.now(), false);
            this.manejador.anadirDocumentoA_Lista(nuevoDoc);
            crearCarpetasDeDireccion(direccion, nuevoNodo);//Se crean las carpetas . y ..
            this.manejador.getMiTerminal().actualizarArbol();
        }

    }
    
        public void crearCarpetasConDireccion(String direccion,ManejadorCD manejadorCd){
        ManejadorDeDirecciones manejadorDirecciones = new ManejadorDeDirecciones(manejador);
        String[] direcciones=manejadorDirecciones.buscarNombreDeArchivo(direccion);
        if(manejadorCd.buscarDireccion(direcciones[1],false)){//La direccion existe y se puede crear el archivo
                crearDirectorioSoloConId(direcciones[0],direcciones[1]);
        }//Cd informar si no existe el directorio
    }

}
