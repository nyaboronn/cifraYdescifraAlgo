
package Ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *  Funciones auxiliares para procesar los archivos
 * 
 * @author nyaboron
 */
public class ProcesaCosas {
    
    public String leerFicheroTexto(String ruta) {
        
        String texto = "";
        
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        
        try {
           archivo = new File (ruta);
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);
           
           String linea;
           while((linea=br.readLine())!=null) {
               texto += linea;
           }
        } catch(IOException e) {
        } finally {
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (IOException e2){}
        }
        
        return texto;
    }
 
    /*
        remplazar todo lo que no sean letras y numeros por cadena vacia
    */
    public String limpiarTexto(String texto) {
        return texto.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }
    
}
