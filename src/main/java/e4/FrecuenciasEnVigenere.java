package e4;

import Ficheros.ProcesaCosas;
import e2.Vigenere;
import e3.FrecuenciasEnDesplazamiento;
import java.util.ArrayList;
import java.util.List;

/**
 * Criptoanálisis del cifrado de Vigenère por análisis de frecuencias
 * 
 * @author nyaboron
 */
public class FrecuenciasEnVigenere {

    Double F_EN_INGLES;     // frecuencia de la i-ésima letra en un texto inglés normal
    int N;                  // letras del abcedario ingles
    final int TMAX = 9;     // ulitmo periodo a calcular
    FrecuenciasEnDesplazamiento EJERCICIO3; // reutilizar codigo del ejercicio anterior

    public FrecuenciasEnVigenere() {
        N = 26;
        EJERCICIO3 = new FrecuenciasEnDesplazamiento();
        F_EN_INGLES = EJERCICIO3.verificarTablaDeFrecuenciasIngles();
    }

    /*
        Criptoanálisis del cifrado de Vigenère por análisis de frecuencias
    */
    String analisisDeFrecuencias(String texto) {
        String clave = "";
        List<String> textoEnColumnas = new ArrayList();
        
        // Calcular t (periodo)
        int t = obtenerPeriodo(texto);
        System.out.println("\nlongitud de la clave: " + t);
        
        // separar texto en 't' columnas, es copia y pega de la funcon obtenerPeriodo()
        String columna;
        int i;
        int pos;
        int textoLength = texto.length();
        for ( int inicial = 1; inicial <= t; ++inicial ) {
            columna = "";
            i = 0;
            while(true) {
                pos = inicial + i * t;
                if (pos > textoLength) break;
                columna += texto.charAt(pos - 1);
                ++i;
            }
            textoEnColumnas.add(columna);
        }
        System.out.print("\n*** Usando el analasis de frencuencias por 'columna' ***");        
        
        for (i = 0; i < t; ++i) {
            clave += (char) (EJERCICIO3.analisisDeFrecuencias(textoEnColumnas.get(i), false) + 'A');
        }
        return clave;
    }

    int obtenerPeriodo(String texto) {
        List<Double> indexOfCondicence = new ArrayList<>();
        
        // calcular el IC de cada periodo
        for ( int t = 1; t <= TMAX; ++t ) {
            indexOfCondicence.add(calcularIndicePeriodo(t, texto));
        }
        
        // obtener el IC mas cercano a 0.065
        int periodo = 0;
        for (int i = 1; i < indexOfCondicence.size(); ++i) {
            if (Math.abs(F_EN_INGLES - indexOfCondicence.get(i)) < Math.abs(F_EN_INGLES - indexOfCondicence.get(periodo))) {
                periodo = i;
            }
        }
        
        // Sumarle uno ya que la posicion se calculaba en base a la lista
        ++periodo;
        
        return periodo;
    }

    /*
        Calcular el IC de cada columna para el periodo 'periodo'.
        Con solo la primera columna no se conseguia un analisis
        correcto por ello se calcula la media de la IC de todas las columnas
    */
    Double calcularIndicePeriodo(int periodo, String texto) {
        
        String columna;
        int textoLength = texto.length();
        int i = 0;
        int pos;
        Double sumatorio = 0.0;
        
        // Calcular el IC de cada columna
        for ( int inicial = 1; inicial <= periodo; ++inicial ) {

            
            columna = "";
            i = 0;
            // obtener la columna
            while(true) {
                pos = inicial + i * periodo;
                if (pos > textoLength) break;   // controlar que no se salga del String
                columna += texto.charAt(pos - 1);
                ++i;
            }
            
            sumatorio += calcularIndiceCoincidencia(columna);
        }
        
        System.out.println("t: "+ periodo + " => Ic = " + sumatorio / (periodo + 0.0));
        
        if (sumatorio == 0.0) return 0.0;
        
        // calcular la media de los IC
        return sumatorio / (periodo + 0.0);
    }
    
    
    /*
        Dado un texto calcular su IC
        https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-IOC.html
    */
    Double calcularIndiceCoincidencia(String texto) {
        Double sumatorio = 0.0;
        Double fi = 0.0;
        Double totalLetras = texto.length() + 0.0;
        
        for (int i = 0; i < N; ++i) {
            fi = EJERCICIO3.contarOcurrenciasEnTexto(texto, i + 'A') + 0.0;
            sumatorio += fi*(fi-1);
        }
        
        sumatorio = sumatorio / (totalLetras*(totalLetras-1));
        
        return sumatorio;
    }

    public static void main(String args[]) {
        ProcesaCosas operacionesAuxiliares = new ProcesaCosas();
        FrecuenciasEnVigenere EJERCICIO4 = new FrecuenciasEnVigenere();

        if (args.length != 1) {
            System.out.println("Error en los argumentos (rutadelfichero)");
            System.exit(0);
        }
        
        String cifrado = operacionesAuxiliares.leerFicheroTexto(args[ 0 ]);
        
        if(cifrado.contentEquals("")) {
            System.out.println("Error al leer el fichero");
            System.exit(0);
        }
                
        String cifradolimpio = operacionesAuxiliares.limpiarTexto(cifrado);

        System.out.println("*** Mensaje cifrado ***");
        System.out.println(cifradolimpio);

        System.out.println("\n*** Indices de coincidencia para cada periodo 't' ***");
        String clave = EJERCICIO4.analisisDeFrecuencias(cifradolimpio);

        System.out.println("\nclave: " + clave);

        System.out.println("\n*** Mensaje descifrado ***");
        Vigenere vigenereSimple = new Vigenere();
        System.out.println(vigenereSimple.descifrarTexto(cifradolimpio, clave, vigenereSimple.getN()));
    }
}
