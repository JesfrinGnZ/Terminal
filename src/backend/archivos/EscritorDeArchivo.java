/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.archivos;

import backend.manejoDeDatos.Documento;
import backend.manejoDeDatos.ManejadorDeArboles;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author jesfrin
 */
public class EscritorDeArchivo {

    public static void escribirEnArchivo(String pathname, ManejadorDeArboles manejador) throws IOException {
        File path = new File(pathname);
        FileWriter fr = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fr);
        for (int i = 1; i < manejador.getDocumentos().size(); i++) {
            String esFolder = "false";
            Documento documento = manejador.getDocumentos().get(i);
            if (documento.esFolder()) {
                esFolder = "true";
            }
            bw.write("direccion:" + documento.getDireccion() + " " + "direccionDePadre:" + documento.getDireccionDePadre() + " "
                    + "nombre:" + documento.getNombre() + " " + "permisos:" + documento.getPermisos() + " " + "esFolder:" + esFolder + " "
                    + "tamano:" + documento.getTamano() + " " + "fechaDeCreacion:" + recomponerFecha(documento.getFechaDeCreacion()) + " "
                    + "hora:" + recomponerHora(documento.getHoraDeCreacion()) +" "+"oculto:"+ documento.esOculto()+" "+"\n");
//            System.out.println("direccion:"+documento.getDireccion()+" "+"direccionDePadre:"+documento.getDireccionDePadre()+" "+
//                    "nombre:"+documento.getNombre()+" "+"permisos:"+documento.getPermisos()+" " +"esFolder:"+esFolder+" "+
//                    "tamano:"+documento.getTamano()+" "+"fechaDeCreacion:"+documento.getFechaDeCreacion()+" "+
//                    "hora:"+documento.getHoraDeCreacion()+"\n");
        }
        //direccion:/home direccionDePadre:/ nombre:home permisos:-rwx esFolder:true tamano:1 fechaDeCreacion:15/02/2019 hora: 10:10
        //bw.append(texto);
        bw.close();
    }

    private static String recomponerFecha(LocalDate fecha) {
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        return String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + "19";
    }

    private static String recomponerHora(LocalTime tiempo) {
        int hora = tiempo.getHour();
        int minutos = tiempo.getMinute();
        String min;
        if (minutos < 10) {
            min = "0" + String.valueOf(minutos);
            return String.valueOf(hora) + ":" + min;
        }
        return String.valueOf(hora) + ":" + String.valueOf(minutos);
    }

}
