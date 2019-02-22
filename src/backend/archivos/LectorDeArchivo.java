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
public class LectorDeArchivo {
    
    public static String leerArchivo(String pathname) throws FileNotFoundException,IOException{
        File path = new File(pathname);
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        String concatenacion="";
        while((linea = br.readLine())!= null){
            concatenacion+=linea;
        }
        fr.close();
        return concatenacion;
//        StringReader sr = new StringReader(concatenacion);
//        System.out.println(sr);
    }
    
}
