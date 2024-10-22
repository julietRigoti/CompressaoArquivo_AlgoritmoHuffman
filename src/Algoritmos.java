import java.util.*;
import java.io.*;


public class Algoritmos {
    static arvBinaria arv = new arvBinaria();
    static HashMap<String, Integer> frequencia = new HashMap<String, Integer>();
    static HashMap<String, String> dicionario = new HashMap<String, String>();
    static PriorityQueue<arvBinaria> fila = new PriorityQueue<arvBinaria>();
    static BitSet bitSet = new BitSet();


    public static void compactarCaractere(Arquivo Arquivo) throws IOException {
        System.out.println("Compactar por caractere");
        File arqComp = new File(Arquivo.getNomeArquivo() +"_compactado.bin");

        Arquivo.lerArquivoCaractere(frequencia, fila);
        
        arv = arv.criaFilaArvBinaria(fila);

        if (arqComp.exists() && !arqComp.delete()) {
            System.out.println("Erro ao deletar o arquivo existente.");
            return;
        }
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arqComp))) {
            arqComp.createNewFile();
            o.writeObject(arv);
        } catch (IOException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }

        arv.CodigoBinarioHash(dicionario,"", 1);
        processarArquivoTexto(Arquivo.getNomeArquivo() ,dicionario, bitSet);

        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arqComp, true))) {
           o.writeObject(bitSet.toByteArray());
           o.close();
        } catch (IOException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
        
    }

    private static void processarArquivoTexto(String nomeArquivo, HashMap<String, String> dicionario, BitSet bitSet) {
        int n = 0;  

        try {
            BufferedReader lerArq = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), "UTF-8"));

            int ch = lerArq.read();  
            while (ch != -1) {
                String codigo = dicionario.get(String.valueOf((char) ch)); 
                for(int i = 0; i < codigo.length(); i++) {
                    if (codigo.charAt(i) == '1') {
                        bitSet.set(n++, true);
                    } else {
                        bitSet.set(n++, false);
                    }
                }
                ch = lerArq.read();
            }
            bitSet.set(n, true);  
            lerArq.close();
        } catch (IOException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    public static void compactarPalavra(Arquivo Arquivo) {
        System.out.println("Compactar por palavra");
    }

    public static void descompactarCaractere(Arquivo Arquivo) throws IOException {
        System.out.println("Descompactar por caractere");


    }

    public static void descompactarPalavra() {
        System.out.println("Descompactar por palavra");
    }

}
