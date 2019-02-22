/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.archivos;

import java.io.*;

/**
 *
 * @author jesfrin
 */
public class ModificadorDeArchivos {
    
    public static void modificarArchivo(String pathname) throws IOException{
        File path = new File(pathname);
        RandomAccessFile archivo = new RandomAccessFile(path, "rw");
        archivo.seek(15);
        archivo.writeBytes("HOLA MUNDO");
        archivo.close();
    }
}
