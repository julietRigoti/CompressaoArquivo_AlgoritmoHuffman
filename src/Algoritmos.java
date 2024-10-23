import java.util.*;
import java.io.*;

public class Algoritmos {
    static arvBinaria arv = new arvBinaria();
    static HashMap<String, Integer> frequencia = new HashMap<String, Integer>();
    static HashMap<String, String> dicionario = new HashMap<String, String>();
    static Queue<arvBinaria> fila = new LinkedList<>();
    static BitSet bitSet = new BitSet();

    // Método para compactar um arquivo de texto baseado em caracteres.
    // pré-condição: O objeto Arquivo deve ser válido e apontar para um arquivo existente.
    // pós-condição: O arquivo é compactado e salvo como "compactadoCaractere.bin".
    public static void compactarCaractere(Arquivo Arquivo) throws IOException {
        File arqComp = new File("compactadoCaractere.bin");

        Arquivo.lerArquivoCaractere(frequencia, fila);

        arv = arv.criaFilaArvBinaria(fila);

        if (arqComp.exists() && !arqComp.delete()) {
            System.out.println("Erro ao deletar o arquivo existente.");
            return;
        }

        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arqComp))) {
            arqComp.createNewFile();

            o.writeObject(arv);

            arv.CodigoBinarioHash(dicionario, "", 1);

            processarArquivoCaractere(Arquivo.getNomeArquivo(), dicionario, bitSet);

            o.writeObject(bitSet);

        } catch (IOException erro) {
            System.out.println("Erro ao compactar: " + erro.getMessage());
        }
    }

    // Processa o arquivo e converte os caracteres para seus códigos binários correspondentes.
    // pré-condição: nomeArquivo deve ser um arquivo existente e dicionario deve estar preenchido.
    // pós-condição: Os códigos binários dos caracteres são armazenados no BitSet.
    private static void processarArquivoCaractere(String nomeArquivo, HashMap<String, String> dicionario,
            BitSet bitSet) {
        int n = 0;

        try {
            BufferedReader lerArq = new BufferedReader(
                    new InputStreamReader(new FileInputStream(nomeArquivo), "UTF-8"));

            int ch = lerArq.read();
            while (ch != -1) {
                String codigo = dicionario.get(String.valueOf((char) ch));
                for (int i = 0; i < codigo.length(); i++) {
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

    // Método para descompactar um arquivo compactado baseado em caracteres.
    // pré-condição: O arquivo de origem deve existir.
    // pós-condição: O conteúdo descompactado é salvo em "descompactadoCaractere.txt".
    public static void descompactarBinarioCaractere(Arquivo Arquivo) throws ClassNotFoundException, IOException {

        File arqDescomp = new File(Arquivo.getNomeArquivo());

        if (!arqDescomp.exists()) {
            System.out.println("Arquivo de origem não encontrado: " + arqDescomp.getName());
            return;
        }
        
        File arqDescompTexto = new File("descompactadoCaractere.txt");
        if (!arqDescompTexto.exists()) {
            if (arqDescompTexto.createNewFile()) {
            } else {
                System.out.println("Erro ao criar o arquivo de saída.");
                return;
            }
        }

        descompactarGeral(arqDescomp, arqDescompTexto);
    }

    // Processa o arquivo e converte palavras para seus códigos binários correspondentes.
    // pré-condição: nomeArquivo deve ser um arquivo existente e dicionario deve estar preenchido.
    // pós-condição: Os códigos binários das palavras são armazenados no BitSet.
    private static void processarArquivoPalavra(String nomeArquivo, HashMap<String, String> dicionario, BitSet bitSet) {
        int n = 0;

        try {
            BufferedReader lerArq = new BufferedReader(
                    new InputStreamReader(new FileInputStream(nomeArquivo), "UTF-8"));

            StringBuilder palavra = new StringBuilder();
            int ch = lerArq.read();
            while (ch != -1) {
                if (Character.isLetterOrDigit(ch)) {
                    palavra.append((char) ch);

                } else {
                    // Quando encontrar um separador, processa a palavra
                    if (palavra.length() > 0) {
                        String codigo = dicionario.get(palavra.toString());

                        for (int i = 0; i < codigo.length(); i++) {
                            if (codigo.charAt(i) == '1') {
                                bitSet.set(n++, true);
                            } else {
                                bitSet.set(n++, false);
                            }

                        }
                        palavra.delete(0, palavra.length());
                    }
                    // Processar o caractere especial (espaço, pontuação etc.)
                    String simboloEspecial = Character.toString((char) ch);
                    String codigoSimbolo = dicionario.get(simboloEspecial);
                    if (codigoSimbolo != null) {
                        for (int i = 0; i < codigoSimbolo.length(); i++) {
                            bitSet.set(n++, codigoSimbolo.charAt(i) == '1');
                        }
                    }
                }

                ch = lerArq.read();
            }

            if (palavra.length() > 0) {
                String codigo = dicionario.get(palavra.toString());

                for (int i = 0; i < codigo.length(); i++) {
                    if (codigo.charAt(i) == '1') {
                        bitSet.set(n++, true);
                    } else {
                        bitSet.set(n++, false);
                    }
                }

            }
            bitSet.set(n, true);
            lerArq.close();
        } catch (

        IOException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    // Método para compactar um arquivo de texto baseado em palavras.
    // pré-condição: O objeto Arquivo deve ser válido e apontar para um arquivo existente.
    // pós-condição: O arquivo é compactado e salvo como "compactadoPalavra.bin".
    public static void compactarPalavra(Arquivo Arquivo) throws IOException {
        File arqComp = new File("compactadoPalavra.bin");
        Arquivo.lerArquivoPalavra(frequencia, fila);

        arv = arv.criaFilaArvBinaria(fila);

        if (arqComp.exists() && !arqComp.delete()) {
            System.out.println("Erro ao deletar o arquivo existente.");
            return;
        }

        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(arqComp))) {
            arqComp.createNewFile();
            o.writeObject(arv);

            arv.CodigoBinarioHash(dicionario, "", 1);

            processarArquivoPalavra(Arquivo.getNomeArquivo(), dicionario, bitSet);
            o.writeObject(bitSet);

        } catch (IOException erro) {
            System.out.println("Erro ao compactar: " + erro.getMessage());
        }
    }
    
    // Método para descompactar um arquivo compactado baseado em palavras.
    // pré-condição: O arquivo de origem deve existir.
    // pós-condição: O conteúdo descompactado é salvo em "descompactadoPalavra.txt".
    public static void descompactarBinarioPalavra(Arquivo Arquivo) throws ClassNotFoundException, IOException {
        File arqDescomp = new File(Arquivo.getNomeArquivo());

        if (!arqDescomp.exists()) {
            System.out.println("Arquivo de origem não encontrado: " + arqDescomp.getName());
            return;
        }

        // Tentativa de criar o arquivo de saída para armazenar o conteúdo descompactado
        File arqDescompTexto = new File("descompactadoPalavra.txt");
        if (!arqDescompTexto.exists()) {
            if (arqDescompTexto.createNewFile()) {
                System.out.println("Arquivo de saída criado: " + arqDescompTexto.getName());
            } else {
                System.out.println("Erro ao criar o arquivo de saída.");
                return;
            }
        }
        
        descompactarGeral(arqDescomp, arqDescompTexto);

    }

    // Método geral para descompactar arquivos. Lê a árvore e o BitSet, e gera o texto descompactado.
    // pré-condição: arqDescomp deve existir e ser um arquivo válido.
    // pós-condição: O conteúdo descompactado é salvo no arquivo de saída.
    public static void descompactarGeral(File arqDescomp, File arqDescompTexto)throws ClassCastException, IOException{

     // Tentativa de abrir o arquivo compactado e ler os objetos serializados
        try (ObjectInputStream o = new ObjectInputStream(new FileInputStream(arqDescomp))) {

            arvBinaria arv = (arvBinaria) o.readObject();

            BitSet bitSet;
            try {
                bitSet = (BitSet) o.readObject();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Erro ao ler o BitSet: " + e.getMessage());
                return;
            }

            StringBuilder textoDescompactado = new StringBuilder();
            arvBinaria atual = arv;

            for (int i = 0; i < bitSet.length() - 1; i++) {

                atual = bitSet.get(i) ? atual.getDir() : atual.getEsq();

                if (atual.getEsq() == null && atual.getDir() == null) {
                    String palavraDecodificada = atual.getInfo();
                    textoDescompactado.append(palavraDecodificada);
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

}
