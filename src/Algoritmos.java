import java.util.*;
import java.io.*;


public class Algoritmos {
    static arvBinaria arv = new arvBinaria();
    static HashMap<String, Integer> frequencia = new HashMap<String, Integer>();
    static HashMap<String, String> dicionario = new HashMap<String, String>();
    static Queue<arvBinaria> fila = new LinkedList<>();
    static BitSet bitSet = new BitSet();

    public static void compactarCaractere(Arquivo Arquivo) throws IOException {
        System.out.println("Compactar por caractere");
        File arqComp = new File("arq_compactado.bin");

        Arquivo.lerArquivoCaractere(frequencia, fila);
        
        arv = arv.criaFilaArvBinaria(fila);

        if (arqComp.exists() && !arqComp.delete()) {
            System.out.println("Erro ao deletar o arquivo existente.");
            return;
        }
        // Abre o fluxo para escrever tanto a árvore quanto o BitSet no mesmo arquivo
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arqComp))) {
            arqComp.createNewFile();
            
            // Escreve a árvore binária
            o.writeObject(arv);
            System.out.println("Árvore binária gravada com sucesso.");

            // Gera o dicionário de códigos binários
            arv.CodigoBinarioHash(dicionario, "", 1);

            // Processa o arquivo de entrada e gera o BitSet
            processarArquivoTexto(Arquivo.getNomeArquivo(), dicionario, bitSet);

            // Escreve o BitSet no mesmo fluxo
            System.out.println("Escrevendo BitSet no arquivo. Conteúdo do BitSet: " + bitSet);
            o.writeObject(bitSet);
            
            // Confirmação de que o arquivo foi gravado e fechado
            System.out.println("BitSet gravado com sucesso.");
        } catch (IOException erro) {
            System.out.println("Erro ao compactar: " + erro.getMessage());
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

    public static void descompactarCaractere(Arquivo Arquivo) throws ClassNotFoundException, IOException {
        System.out.println("Iniciando descompactação por caractere");
    
        File arqDescomp = new File(Arquivo.getNomeArquivo());
    
        if (!arqDescomp.exists()) {
            System.out.println("Arquivo de origem não encontrado: " + arqDescomp.getName());
            return;
        }
    
        // Tentativa de criar o arquivo de saída para armazenar o conteúdo descompactado
        File arqDescompTexto = new File("arq_descompactado.txt");
        if (!arqDescompTexto.exists()) {
            if (arqDescompTexto.createNewFile()) {
                System.out.println("Arquivo de saída criado: " + arqDescompTexto.getName());
            } else {
                System.out.println("Erro ao criar o arquivo de saída.");
                return;
            }
        }
    
        // Tentativa de abrir o arquivo compactado e ler os objetos serializados
        try (ObjectInputStream o = new ObjectInputStream(new FileInputStream(arqDescomp));
             OutputStreamWriter escrever = new OutputStreamWriter(new FileOutputStream(arqDescompTexto), "UTF-8")) {
    
            arvBinaria arv = (arvBinaria) o.readObject();
        
            BitSet bitSet;
            try {
                bitSet = (BitSet) o.readObject();
                System.out.println("BitSet lido com sucesso.");
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Erro ao ler o BitSet: " + e.getMessage());
                return;
            }
    
            
            StringBuilder textoDescompactado = new StringBuilder();
            arvBinaria atual = arv;
    

            for (int i = 0; i < bitSet.length(); i++) {
                atual = bitSet.get(i) ? atual.getDir() : atual.getEsq(); 
                if (atual.getEsq() == null && atual.getDir() == null) {
                    textoDescompactado.append(atual.getInfo());
                    atual = arv;  
                }
            }
    
            // Escrever o texto descompactado no arquivo de saída
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arqDescompTexto))) {
                writer.write(textoDescompactado.toString());
            }
    
        } catch (IOException e) {
            System.out.println("Erro de IO durante a descompactação: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao ler objetos do arquivo: " + e.getMessage());
        }
    }

    public static void compactarPalavra(Arquivo Arquivo) {
        System.out.println("Compactar por palavra");
    }

    public static void descompactarPalavra() {
        System.out.println("Descompactar por palavra");
    }

}
