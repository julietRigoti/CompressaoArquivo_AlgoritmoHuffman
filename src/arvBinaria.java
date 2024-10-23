import java.util.*;
import java.io.Serializable;

public class arvBinaria implements Comparable<arvBinaria>, Serializable {
        private static final long serialVersionUID = 1L;    
        private String info;
        private int freq;
        private arvBinaria esq;
        private arvBinaria dir;

        // Construtor padrão que inicializa um nó vazio.
        // pré-condição: Nenhuma.
        // pós-condição: Um objeto arvBinaria com info e freq inicializados para valores padrão ("" e 0).
        public arvBinaria() {
            this.info = "";
            this.freq = 0;
            this.esq = null;
            this.dir = null;
        }

        // Construtor que inicializa um nó com informação e frequência especificadas.
        // pré-condição: info não deve ser nulo e freq deve ser um valor válido.
        // pós-condição: Um objeto arvBinaria com info e freq definidos.
        public arvBinaria(String info, int freq) {
            this.info = info;
            this.freq = freq;
            this.esq = null;
            this.dir = null;
        }

        // Construtor que inicializa um nó com frequência e referências para filhos esquerdo e direito.
        // pré-condição: esq e dir podem ser nulos ou referências a outros nós arvBinaria.
        // pós-condição: Um objeto arvBinaria com freq e referências para os nós filhos definidos.
        public arvBinaria(int freq, arvBinaria esq, arvBinaria dir) {
            this.info = "";
            this.freq = freq;
            this.esq = esq;
            this.dir = dir;
        
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getInfo() {
            return this.info;
        }

        public void setFreq(int freq) {
            this.freq = freq;
        }

        public int getFreq() {
            return this.freq;
        }

        public void setEsq(arvBinaria esq) {
            this.esq = esq;
        }

        public arvBinaria getEsq() {
            return this.esq;
        }

        public void setDir(arvBinaria dir) {
            this.dir = dir;
        }

        public arvBinaria getDir() {
            return this.dir;
        }

        // Adiciona um novo nó à árvore binária com a informação e frequência especificadas.
        // pré-condição: info não deve ser nulo e freq deve ser um valor válido.
        // pós-condição: O novo nó é inserido na posição correta da árvore conforme a frequência.
        public void add(String info, int freq){
            if(this.info == "" && this.freq == 0){
                this.info = info;
                this.freq = freq;
            }
        else {
            if(this.freq >freq){
                if(this.esq == null){
                    this.esq = new arvBinaria(info, freq);
                }
                else{
                    this.esq.add(info, freq);
                }
            }
            else{
                if(this.dir == null){
                    this.dir = new arvBinaria(info, freq);
                }
                else{
                    this.dir.add(info, freq);
                }
            }
        }
    }

    // Cria uma árvore binária a partir de uma fila de nós.
    // pré-condição: A fila deve conter pelo menos dois nós.
    // pós-condição: Retorna a raiz da árvore binária criada a partir da combinação dos nós na fila.
    public arvBinaria criaFilaArvBinaria(Queue<arvBinaria> fila){
        while(fila.size() > 1){
            arvBinaria arv1 = fila.element();
            fila.remove();
            arvBinaria arv2 = fila.element();
            fila.remove();
            arvBinaria arv = new arvBinaria(arv1.getFreq() + arv2.getFreq(), arv1, arv2);
            fila.add(arv);
            Collections.sort((List<arvBinaria>) fila);
        }
        return fila.element();
    }

    // Compara este nó com outro nó com base na frequência.
    // pré-condição: o deve ser uma instância válida de arvBinaria.
    // pós-condição: Retorna um valor negativo, zero ou positivo conforme a frequência do nó atual é menor, igual ou maior que a do nó fornecido.
    @Override
	public int compareTo(arvBinaria o) {
		// TODO Auto-generated method stub
		if(this.freq == o.freq) {
			return 0;
		}else if(this.freq < o.freq) {
			return -1;
		}
		return 1;
	}

    // Gera um dicionário de códigos binários a partir da árvore binária.
    // pré-condição: dicionario deve ser uma referência válida para um HashMap.
    // pós-condição: O dicionário é preenchido com códigos binários associados à informação do nó.
    public void CodigoBinarioHash(HashMap<String, String> dicionario, String codigo, int lado){

        if(this.esq != null){
            this.esq.CodigoBinarioHash(dicionario, codigo+"0", lado);
        }
        if(this.info != null){
            if(lado == 1){
                dicionario.put(this.info, codigo);
            } else {
                dicionario.put(codigo, this.info);
            }
        }
        if(this.dir != null){
            this.dir.CodigoBinarioHash(dicionario, codigo+"1", lado);
        }
    }

}

