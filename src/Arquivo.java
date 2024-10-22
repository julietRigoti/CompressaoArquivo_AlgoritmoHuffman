import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.PriorityQueue;

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
    
   public void lerArquivoCaractere(HashMap<String, Integer> frequencia, PriorityQueue<arvBinaria> fila){

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
                    frequencia.put(str, frequencia.get(str) + 1);
                }  
                caractere = arq.read();
            }
            arq.close();
        }
        catch(Exception e){
            System.out.println("Erro ao abrir arquivo");
        }
        frequencia.forEach((k, v) -> {
            arvBinaria arv = new arvBinaria(k, v);
            fila.add(arv);
        });
    }
    
    public void lerArquivoPalavra(){
        // Ler arquivo palavra  
   }
}
