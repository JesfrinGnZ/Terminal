/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import gui.Frame.TerminalGui;
import backend.archivos.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import backend.analizadores.AnalizadorLexicoArchivo;
import backend.analizadores.parser;
import backend.manejoDeDatos.ManejadorDeArboles;
import java.io.BufferedReader;
import java.io.StringReader;

/**
 *
 * @author jesfrin
 */
public class Terminal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TerminalGui miFrame = new TerminalGui();        
       
        try {
            String datos =LectorDeArchivo.leerArchivo("/home/jesfrin/Documentos/Textos/Folders");
            
        ManejadorDeArboles nuevoManejador = new ManejadorDeArboles(miFrame);
        miFrame.setNuevoManejador(nuevoManejador);
//        
//        String datos ="direccion:/home direccionDePadre:/ nombre:Jesfrin permisos:-rwx esFolder:true tamano:1 fechaDeCreacion:15/02/2019";
//        //String datos = "/home/jesfrin/hola";
        AnalizadorLexicoArchivo lexico = new AnalizadorLexicoArchivo(new BufferedReader(new StringReader(datos)));
        parser sintactico = new parser(lexico,nuevoManejador);
        try {
            sintactico.parse();
        } catch (Exception ex) {
            Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
        }
//            //System.out.println("BUCLE");
//        nuevoManejador.mostrarInformacionDeDocumentos();
//      
//        //trabajoDeArchivos();
//
//        // Construccion del arbol

       miFrame.setVisible(true);
       
       //nuevoManejador.mostrarInformacionDeDocumentos();

        } catch (IOException ex) {
            Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

//public static void main(String[] args){
//        
//    Date fecha = new Date(2019, 02, 15);
//    System.out.println(fecha.getDate()+" "+fecha.getMonth()+" "+fecha.getYear());
//}
    
    public static void trabajoDeArchivos() {
        String pathname = "/home/jesfrin/pruebas/archivo.txt";
        try {
            System.out.println("Lectura 1");
            LectorDeArchivo.leerArchivo(pathname);
            EscritorDeArchivo.escribirEnArchivo(pathname, pathname);
            System.out.println("Lectura 2");
            LectorDeArchivo.leerArchivo(pathname);
            ModificadorDeArchivos.modificarArchivo(pathname);
            System.out.println("Lectura 3");
            LectorDeArchivo.leerArchivo(pathname);
        } catch (IOException ex) {
            Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
