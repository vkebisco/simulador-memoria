package com.malloc.malloc.diverse;

import com.malloc.malloc.domain.Particao;
import com.malloc.malloc.domain.Processo;

import java.util.List;

public class Simulador {

    private int memoria; //tamanho do processo

    private List<Particao> particoes;

    private Alocacao tipoAlocacao;

    public Simulador() {
    }

    public Simulador(int memoria, List<Particao> particoes, Alocacao tipoAlocacao) {
        this.memoria = memoria;
        this.particoes = particoes;
        this.tipoAlocacao = tipoAlocacao;
    }

    public Object[] alocar(Processo p){
        int ind = -1;
        switch (tipoAlocacao){
            case BESTFIT:
                ind = bestFit(p.getTamanho());
                break;
            case WORSTFIT:
                ind = worstFit(p.getTamanho());
                break;
            case FIRSTFIT:
                ind = firstFit(p.getTamanho());
                break;
        }

        return new Object[]{mover(p,ind), ind} ;
    }

    public boolean mover(Processo processo, int ind){

        if (ind != -1){
            Particao escolhido = this.particoes.get(ind);
            int start = escolhido.getEnderecoDeInicio();

            Particao newPartition = new Particao(start, escolhido.getTamanho(), false ,processo);

            this.particoes.remove(ind);
            this.particoes.add(ind, newPartition);


            //alocação particionada dinamica
           /*  int restante = escolhido.getTamanho() - processo.getTamanho();
             if (restante != 0){
                int inicioFree = start + processo.getTamanho();
                this.particoes.add(ind+1, new Particao(inicioFree, restante, true));
            } */
            return true;
        }
        return false;
    }

    public List<Particao> desalocar(int index){
        var p =this.particoes.get(index);
        p.setProcesso(null);
        p.setFree(true);
        return this.particoes;
    }

    private int bestFit(int tamanhoProcesso){
        int ind = -1, mn = 1000000;
        for(int i=0; i<particoes.size(); ++i){
            if (particoes.get(i).isFree()){
                if (particoes.get(i).getTamanho() >= tamanhoProcesso && particoes.get(i).getTamanho() < mn){
                    mn = particoes.get(i).getTamanho();
                    ind = i;
                }
            }
        }

        return ind;
    }

    private int worstFit(int tamanhoProcesso){
        int ind = -1, mx = 0;
        for(int i=0; i<particoes.size(); ++i){
            if (particoes.get(i).isFree()){
                if (particoes.get(i).getTamanho() >= tamanhoProcesso && particoes.get(i).getTamanho() > mx){
                    mx = particoes.get(i).getTamanho();
                    ind = i;
                }
            }
        }

        return ind;
    }

    private int firstFit(int tamanhoProcesso){
        for(int i=0; i<particoes.size(); ++i){
            if (particoes.get(i).isFree()){
                if (particoes.get(i).getTamanho() >= tamanhoProcesso){
                    return i;
                }
            }
        }
        return -1;
    }

    public Alocacao getTipoAlocacao() {
        return tipoAlocacao;
    }

    public void setTipoAlocacao(Alocacao tipoAlocacao) {
        this.tipoAlocacao = tipoAlocacao;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public List<Particao> getParticoes() {
        return particoes;
    }

    public void setParticoes(List<Particao> particoes) {
        this.particoes = particoes;
    }
}
