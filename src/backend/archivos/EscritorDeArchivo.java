/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author jesfrin
 */
public class EscritorDeArchivo {
    
    public static void escribirEnArchivo(String pathname, String texto) throws IOException{
        File path = new File(pathname);
        FileWriter fr = new FileWriter(path,true);
        BufferedWriter bw = new BufferedWriter(fr);
        //bw.newLine();
        bw.append(texto);
        bw.close();
    }
}
