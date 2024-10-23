import java.util.*;
import java.io.*;

public class Arquivo {
    private String nomeArquivo;

    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    
    // Lê o arquivo e conta a frequência de cada caractere.
    // pré-condição: frequencia deve ser um HashMap válido e fila deve ser uma Queue.
    // pós-condição: A frequência de cada caractere é armazenada no HashMap e nós são adicionados à fila.
   public void lerArquivoCaractere(HashMap<String, Integer> frequencia, Queue<arvBinaria> fila){

        try{

            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo), "UTF-8")); 

            int caractere = arq.read();
            while(caractere != -1){
                char c = (char) caractere;
                String str = String.valueOf(c);
                if(frequencia.get(str) == null){ 
                    frequencia.put(str, 1);
                }
                else{
                    frequencia.replace(str, frequencia.get(str) + 1);
                }  
                caractere = arq.read();
            }
            arq.close();

        }
        catch(IOException e){
            System.out.println("Erro: " + e.getMessage());
        }

        frequencia.forEach((k, v) -> {
            arvBinaria arv = new arvBinaria(k, v);
            fila.add(arv); 
        });

        Collections.sort((List<arvBinaria>) fila);

    }
    // Lê o arquivo e conta a frequência de cada palavra.
    // pré-condição: frequencia deve ser um HashMap válido e fila deve ser uma Queue.
    // pós-condição: A frequência de cada palavra é armazenada no HashMap e nós são adicionados à fila.
    public void lerArquivoPalavra(HashMap<String, Integer> frequencia, Queue<arvBinaria> fila){

        try{
            StringBuilder palavra = new StringBuilder();

            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo), "UTF-8")); 

            int c = arq.read();
            while(c != -1){
                if(Character.isLetterOrDigit(c)){
                    palavra.append((char) c);
                }
                else{
                    if(frequencia.get(palavra.toString()) == null){ 
                        frequencia.put(palavra.toString(), 1);
                    }
                    else{
                        frequencia.replace(palavra.toString(), frequencia.get(palavra.toString()) + 1);
                    }
                   palavra.delete(0, palavra.length());

                   if(c != -1){
                          palavra.append((char) c);
                          if(frequencia.get(palavra.toString()) == null){ 
                              frequencia.put(palavra.toString(), 1);
                          }
                          else{
                              frequencia.replace(palavra.toString(), frequencia.get(palavra.toString()) + 1);
                          }
                          palavra.delete(0, palavra.length());
                   }

                }
                c = arq.read();
            }
            arq.close();

        }
        catch(IOException e){
            System.out.println("Erro: " + e.getMessage());
        }

        frequencia.forEach((k, v) -> {
            arvBinaria arv = new arvBinaria(k, v);
            fila.add(arv); 
        });

        Collections.sort((List<arvBinaria>) fila);    
   }
}
