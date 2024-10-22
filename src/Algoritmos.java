import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Algoritmos {
    static arvBinaria arv = new arvBinaria();
    static HashMap<String, Integer> frequencia = new HashMap<String, Integer>();
    static PriorityQueue<arvBinaria> fila = new PriorityQueue<arvBinaria>();

    public static void compactarCaractere(Arquivo Arquivo) {
        System.out.println("Compactar por caractere");

        Arquivo.lerArquivoCaractere(frequencia, fila);
        
        arv.criaFilaArvBinaria(fila);

        
    }

    public static void compactarPalavra(Arquivo Arquivo) {
        System.out.println("Compactar por palavra");
    }

    public static void descompactarCaractere() {
        System.out.println("Descompactar por caractere");
    }

    public static void descompactarPalavra() {
        System.out.println("Descompactar por palavra");
    }

}
