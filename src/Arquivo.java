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
    
   public void lerArquivoCaractere(HashMap<String, Integer> frequencia, Queue<arvBinaria> fila){

        try{

            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(this.nomeArquivo), "UTF-8")); 


            int caractere = arq.read();
            while(caractere != -1){
                char c = (char) caractere;
                String str = String.valueOf(c);
                if(frequencia.get(str) == null){ 
                    frequencia.put(str, 1);
                    //System.out.println("Frequencia: " + frequencia.get(str));
                }
                else{
                    frequencia.replace(str, frequencia.get(str) + 1);
                   // System.out.println("Frequencia: " + frequencia.get(str));
                }  
                caractere = arq.read();
                //System.out.println("Caractere: " + caractere);
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
    
    public void lerArquivoPalavra(){
        // Ler arquivo palavra  
   }
}
