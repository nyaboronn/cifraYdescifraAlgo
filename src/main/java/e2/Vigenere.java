
package e2;

import Ficheros.ProcesaCosas;

/**
 * Cifrado de Vigenère
 * 
 * @author nyaboron
 */
public class Vigenere 
{
    int N;      // nº letras del abcedario

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }
    
    public Vigenere() {
        N = 26;
    }
    
    public char cifrar(Character mi, Character ki, int N) 
    {
        return (char) ((mi + ki) % 26 + 'A'); 
    }
    
    public char descifrar(Character ci, Character ki, int N) 
    {
        //return (char)( ( (int)ci + (N - ki) - 65 ) % N + 65 );
        return (char) ((ci + (N - ki)) % N + 'A'); 
    }
    
    public String cifrarTexto(String texto, String clave, int N) {
        String textoCifrado = "";
        
        int cPos = 0;
        for( int tPos = 0; tPos < texto.length(); ++tPos ) {
            if ( cPos == clave.length() ) { cPos = 0; }
            textoCifrado += cifrar(texto.charAt(tPos), clave.charAt(cPos), N);
            ++cPos;
        }
        
        return textoCifrado;
    }
    
    public String descifrarTexto(String texto, String clave, int N) {
        String textoCifrado = "";
        
        int cPos = 0;
        for( int tPos = 0; tPos < texto.length(); ++tPos ) {
            if ( cPos == clave.length() ) { cPos = 0; }
            textoCifrado += descifrar(texto.charAt(tPos), clave.charAt(cPos), N);
            ++cPos;
        }
        
        return textoCifrado;
    }
        
    public static void main(String args[]) 
    {
        Vigenere vigenereSimple = new Vigenere();
        ProcesaCosas procesa = new ProcesaCosas();
        String mensaje;
        String rutaArchivoTexto;
        String operacion;
        String clave;
        String mensajeClaroProcesado;
        String claveProcesada;
        
        String resultado = "operacion no valida (cifrar/descifrar)";
        
        if (args.length != 3) {
            System.out.println("Faltan argumentos (archivo, cifrar/descrifrar, clave)");
            System.exit(0);
        }
        
        rutaArchivoTexto    = args[ 0 ];
        operacion           = args[ 1 ];
        clave               = args[ 2 ];
        
        mensaje = procesa.leerFicheroTexto( rutaArchivoTexto );
        
        if(mensaje.contentEquals("")) {
            System.out.println("Error al leer el fichero");
            System.exit(0);
        }
        
        mensajeClaroProcesado = procesa.limpiarTexto(mensaje);
        claveProcesada = procesa.limpiarTexto(clave);
        
        if( operacion.contentEquals("cifrar")) {
            resultado = vigenereSimple.cifrarTexto(mensaje, claveProcesada, vigenereSimple.getN());
        } else if( operacion.contentEquals("descifrar")) {
            resultado = vigenereSimple.descifrarTexto(mensajeClaroProcesado, claveProcesada, vigenereSimple.getN());
        }
        
        System.out.println( resultado );
    }
}
