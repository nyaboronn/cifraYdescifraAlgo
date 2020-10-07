
package e3;

import Ficheros.ProcesaCosas;
import java.util.ArrayList;
import java.util.List;



/**
 * Criptoanálisis del cifrado de desplazamiento por análisis de frecuencias
 * 
 * @author nyaboron
 */
public class FrecuenciasEnDesplazamiento {
    
    int N;                  // nº letras alfabeto     
    Double F_EN_INGLES;     // frecuencia de la i-ésima letra en un texto inglés normal
    
    public double fLetrasIngles[] = {
        0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 
        0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 
        0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 
        0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150, 
        0.01974, 0.00074
    };
    
    public FrecuenciasEnDesplazamiento() {
        N = 26;
        F_EN_INGLES = verificarTablaDeFrecuenciasIngles();
    }
    
    /* 
        Funcion para verificar el 0.065
    */
    public Double verificarTablaDeFrecuenciasIngles() {
        Double sumatorio = 0.0;
        
        for ( int i = 0; i < N; ++i ) {
            sumatorio += Math.pow(fLetrasIngles[ i ], 2);
        }
        
        return sumatorio;
    }
    
    public int analisisDeFrecuencias(String texto, boolean printCosas) {
        
        // Calcular frencuencias en el texto encriptado
        ArrayList<Double> qi = calcFrecEnTexto(texto);
        
        // Por cada j calcular Ij
        List<Double> Ij = calcIj(qi);
        
        if (printCosas) {
            System.out.println("*** Ij's calculados ***");
            for ( int j = 0; j < Ij.size(); ++j) {
                System.out.println("j: " + j + " --> " + Ij.get(j) );
            }
        }
        
        int cercano = 0;
        
        for ( int i = 1; i < Ij.size(); ++i ) {
            if ( (Math.abs(F_EN_INGLES - Ij.get(i))) < (Math.abs(F_EN_INGLES - Ij.get(cercano)))) {
                cercano = i;
            }
        }
        
        return cercano;
    }
    
    ArrayList<Double> calcIj(ArrayList<Double> qi) {
        ArrayList<Double> Ij = new ArrayList<>();
        Double sumatorio;

        for( int j = 0; j < N; ++j ) {
            sumatorio = 0.0;
            for ( int i = 0; i < N; ++i ) {
                sumatorio += fLetrasIngles[ i ] * qi.get((i+j)%N);
            }
            Ij.add(sumatorio);
        }
        return Ij;
    }
    
    public ArrayList<Double> calcFrecEnTexto(String texto) {
        ArrayList<Double> qi = new ArrayList<>();
        long count;
        long length = texto.length();
        for( int i = 0; i < N; ++i ) {
           count = contarOcurrenciasEnTexto(texto, i+'A');
           qi.add((double)count / (double)length);
        }
        return qi;
    }
    
    public int contarOcurrenciasEnTexto(String texto, int c) {
        return (int) texto.chars().filter(ch -> ch == c).count();
    }
    
    public static void main(String args[]) {
        
        ProcesaCosas procesa = new ProcesaCosas();
        FrecuenciasEnDesplazamiento cifrador = new FrecuenciasEnDesplazamiento();
        
        if (args.length != 1) {
            System.out.println("Error en los argumentos (rutadelfichero)");
            System.exit(0);
        }
        
        String cifrado = procesa.leerFicheroTexto(args[ 0 ]);
        
        if(cifrado.contentEquals("")) {
            System.out.println("Error al leer el fichero");
            System.exit(0);
        }
        
        String cifradolimpio = procesa.limpiarTexto(cifrado);
        
        System.out.println("*** Verificar la tabla: ***");
        System.out.println("valor: " + cifrador.verificarTablaDeFrecuenciasIngles() + '\n');
        System.out.println("\n*** Analisis de frecuencias *** \n K: " + cifrador.analisisDeFrecuencias(cifradolimpio, true));
    }
    
}
