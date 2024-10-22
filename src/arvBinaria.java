import java.util.PriorityQueue;

public class arvBinaria {
        private String info;
        private int freq;
        private arvBinaria esq;
        private arvBinaria dir;

        public arvBinaria() {
            this.info = "";
            this.freq = 0;
            this.esq = null;
            this.dir = null;
        }
        public arvBinaria(String info, int freq) {
            this.info = info;
            this.freq = freq;
            this.esq = null;
            this.dir = null;
        }

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

    public void inOrdem() {
		if(this.esq != null) {
			this.esq.inOrdem();
		}
		System.out.print(this.info+" ");
		if(this.dir != null) {
			this.dir.inOrdem();
		}
	}

    public arvBinaria criaFilaArvBinaria(PriorityQueue<arvBinaria> fila){
        while(fila.size() > 1){
            arvBinaria arv1 = fila.poll();
            arvBinaria arv2 = fila.poll();
            arvBinaria arv = new arvBinaria(arv1.getFreq() + arv2.getFreq(), arv1, arv2);
            fila.add(arv);
        }
        return fila.element();
    }
}

