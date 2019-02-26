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
import java.util.Random;
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

    public boolean buscarSiArchivoYaExiste(String nombreDeArchivo, String direccionActual) {
        for (Documento documento : this.manejador.getDocumentos()) {
            if (documento.getDireccionDePadre().equals(direccionActual) && documento.getNombre().equals(nombreDeArchivo)) {
                documento.setHoraDeCreacionConHoraDelSistema();
                documento.setFechaDeCreacionConFechaDeSistema();
                return true;
            }
        }
        return false;
    }

    public void crearArchivosSoloConId(String nombreDeHijo) {
        String direccionActual = this.manejador.getMiTerminal().getDireccionActual();
        if (!buscarSiArchivoYaExiste(nombreDeHijo, direccionActual)) {
            if (nombreDeHijo.equals(".") || nombreDeHijo.equals("..")) {//Solo se actualiza el archivo
                int contador = 0;
                for (Documento documento : this.manejador.getDocumentos()) {
                    if (documento.getDireccionDePadre().equals(direccionActual)) {
                        if (documento.getNombre().equals(".") || documento.getNombre().equals("..")) {
                            documento.setHoraDeCreacionConHoraDelSistema();
                            documento.setFechaDeCreacionConFechaDeSistema();
                            contador++;
                        }
                        if (contador == 2) {
                            break;
                        }
                    }
                }
            } else {
                DefaultMutableTreeNode nuevoNodo = new DefaultMutableTreeNode(nombreDeHijo);//Creamos el nodo

                DefaultMutableTreeNode nodo = null;
                for (Documento documento : this.manejador.getDocumentos()) {//Buscamos al padre
                    if (documento.getDireccion().equals(direccionActual)) {
                        nodo = documento.getNodo();
                        break;
                    }
                }
                nodo.add(nuevoNodo);//Anadimos el nodo del hijo al nodo del padre
                this.manejador.getMiTerminal().actualizarArbol();//Actualizamos arbol
                String direccion;
                boolean esOculto = nombreDeHijo.startsWith(".");
                if (direccionActual.equals("/")) {//Restructuramos direccion
                    direccion = direccionActual + nombreDeHijo;
                } else {
                    direccion = direccionActual + "/" + nombreDeHijo;
                }
                Documento nuevoDoc = new Documento(direccion, direccionActual, nombreDeHijo, "-rwx", false, generarTamanoDeArchivo(), LocalDate.now(), nuevoNodo, LocalTime.now(), esOculto);
                nuevoDoc.setPermisos("-rwx");
                this.manejador.anadirDocumentoA_Lista(nuevoDoc);
            }
        }
    }

    private int generarTamanoDeArchivo() {
        int tamano = 1;
        Random r = new Random();
        tamano = r.nextInt(40) + 1;
        return tamano;
    }
}
