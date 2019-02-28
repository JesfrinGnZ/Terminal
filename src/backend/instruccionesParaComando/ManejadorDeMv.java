/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.instruccionesParaComando;

import backend.manejoDeDatos.Documento;
import backend.manejoDeDatos.ManejadorDeArboles;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author jesfrin
 */
public class ManejadorDeMv {
    
    private ManejadorDeArboles manejador;
    
    public ManejadorDeMv(ManejadorDeArboles manejador){
        this.manejador=manejador;
    }
    
    public void cambiarDeNombre(String nombreArchivoActual,String nombreNuevoArchivo){
       String direccionActual = this.manejador.getMiTerminal().getDireccionActual();
       Documento doc = null;
       for (Documento documento : this.manejador.getDocumentos()) {
            if(documento.getDireccionDePadre().equals(direccionActual) && documento.getNombre().equals(nombreArchivoActual)){
                doc =documento;
            }
        }
       if(doc==null){//No existe el id buscado
           this.manejador.getMiTerminal().informarQueNoSeHEncontradoElDirectorio();
       }else if(!doc.esFolder()){
           ManejadorDeDirecciones nuevoManDirecciones = new ManejadorDeDirecciones(manejador);
           Documento docPadre =null;
           for (Documento documento : this.manejador.getDocumentos()) {
               if(documento.getDireccion().equals(direccionActual)){
                   docPadre=documento;
               }
           }
            //Buscar el nodo hijo y eliminarlo
           docPadre.getNodo().remove(doc.getNodo());
           //SE crea un nuevo nodo
           DefaultMutableTreeNode nodoModificado = new DefaultMutableTreeNode(nombreNuevoArchivo);
           //Se ande de nuevo
           docPadre.getNodo().add(nodoModificado);
           //Se modifica en lista el nodo creado
           doc.setNodo(nodoModificado);
           //Se modifica su nombre, y direccion
           doc.setNombre(nombreNuevoArchivo);
           doc.setDireccion(nuevoManDirecciones.buscarNombreDeArchivo(doc.getDireccion())[1]+"/"+nombreNuevoArchivo);
           //Se actualiza el arbol
           this.manejador.getMiTerminal().actualizarArbol();
       }
       
       
    }
    
}
