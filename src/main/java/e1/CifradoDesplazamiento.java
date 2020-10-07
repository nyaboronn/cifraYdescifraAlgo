package e1;

import Ficheros.ProcesaCosas;

/**
 * Cifrado de desplazamiento
 * 
 * @author nyaboron
 */
public class CifradoDesplazamiento {
    
    int N;  // letras del abcdario ingles

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }
    
    public CifradoDesplazamiento() {
        N = 26;
    }
    
    
    public char cifrar(Character mi, int k, int N) 
    {
        return (char)( ( (int)mi + k - 65 ) % N + 65 ); 
        // int charCifrado = ((int)mi + k) % N;
    }
    
    public char descifrar(Character ci, int k, int N) 
    {
        return (char)( ( (int)ci + (N - k) - 65 ) % N + 65 ); 
    }
    
    public String cifrarTexto(String textoLimpio, int k, int N) 
    {
        String textoCifrado = "";
        
        for( int i = 0; i < textoLimpio.length(); ++i ) {
            textoCifrado += cifrar(textoLimpio.charAt(i), k, N);
        }
        
        return textoCifrado;
    }
    
    public String descifrarTexto(String textoCifrado, int k, int N) 
    {
        String textoClaro = "";
        
        for( int i = 0; i < textoCifrado.length(); ++i ) {
            textoClaro += descifrar(textoCifrado.charAt(i), k, N);
        }
        
        return textoClaro;
    }
    
    public static void main(String args[]) 
    {   
        CifradoDesplazamiento desplazamiento = new CifradoDesplazamiento();
        ProcesaCosas procesa = new ProcesaCosas();
        
        String mensaje;
        String rutaArchivoTexto;
        String operacion;
        String mensajeClaroLimpio;
        
        int clave;
        
        String resultado = "operacion no valida (cifrar/descifrar)";
        
        
        if (args.length != 3) {
            System.out.println("Faltan argumentos (archivo, cifrar/descrifrar, clave)");
            System.exit(0);
        }
        
        rutaArchivoTexto = args[ 0 ];
        operacion = args[ 1 ];
        clave = Integer.parseInt(args[ 2 ]);
        
        // pasar a mayusculas y borrar todo lo que no sean letras
        mensaje = procesa.leerFicheroTexto(rutaArchivoTexto);
        
        if(mensaje.contentEquals("")) {
            System.out.println("Error al leer el fichero");
            System.exit(0);
        }
        
        mensajeClaroLimpio = procesa.limpiarTexto(mensaje);
        
        if( operacion.contentEquals("cifrar")) {
            resultado = desplazamiento.cifrarTexto( mensajeClaroLimpio, clave, desplazamiento.getN() );
        } 
        else if( operacion.contentEquals("descifrar")) {
            resultado = desplazamiento.descifrarTexto(mensaje, clave, desplazamiento.getN());
        }
        
        System.out.println(resultado);
    }
    
}
