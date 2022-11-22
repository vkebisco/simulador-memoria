package com.malloc.malloc;

import com.malloc.malloc.domain.Espaco;
import com.malloc.malloc.domain.Memoria;
import com.malloc.malloc.domain.Particao;

import java.util.ArrayList;
import java.util.List;

public class Simulador {

    private static Simulador simulador;
    private int memoria;

    private ArrayList<Particao> particoes;

    private Simulador(int memoria){
        this.memoria = memoria;
        this.particoes = new ArrayList<>();
        this.particoes.add(new Particao(0,memoria, true));
    }

    private Simulador(){
        this.particoes = new ArrayList<>();
        this.particoes.add(new Particao(0,memoria, true));
    }


    public int bestFit(Particao p){
        int ind = -1, min = 1000000;
        for(int i=0; i<particoes.size(); ++i){
            if (particoes.get(i).isFree()){
                if (particoes.get(i).getTamanho() >= p.getTamanho() && particoes.get(i).getTamanho() < min){
                    min = particoes.get(i).getTamanho();
                    ind = i;
                }
            }
        }

        return ind;
    }

    public int worstFit(Particao p){
        int ind = -1, max = 0;
        for(int i=0; i<particoes.size(); ++i){
            if (particoes.get(i).isFree()){
                if (particoes.get(i).getTamanho() >= p.getTamanho() && particoes.get(i).getTamanho() > max){
                    max = particoes.get(i).getTamanho();
                    ind = i;
                }
            }
        }

        return ind;
    }

    public int firstFit(Particao p){
        for(int i=0; i<particoes.size(); ++i){
            if (particoes.get(i).isFree()){
                if (particoes.get(i).getTamanho() >= p.getTamanho()){
                    return i;
                }
            }
        }
        return -1;

    }

    public boolean alloc(int ind, Particao p, int tamanho){
        if (ind != -1){
            Particao escolhido = this.particoes.get(ind);
            int start = escolhido.getEnderecoDeInicio();
            p.setEnderecoDeInicio(start);

            this.particoes.remove(ind);
            this.particoes.add(ind, p);

            int restante = escolhido.getTamanho() - tamanho;
            if (restante != 0){
                int inicioFree = start + tamanho;
                this.particoes.add(ind+1, new Particao(inicioFree, restante, true));
            }
            return true;
        }
        return false;
    }

    public List<Memoria> ShowPartitions(){
        List<Memoria> memorias = new ArrayList<>();

        int start, size;

        for (Particao p : this.particoes) {
            start = p.getEnderecoDeInicio();
            size = p.getTamanho();
            memorias.add(new Memoria(start, (start + size - 1), p.isFree()));
        }
        return memorias;
    }

    public boolean free(int startAddress){
        for (Particao particoe : particoes) {
            if (particoe.getEnderecoDeInicio() == startAddress && !particoe.isFree()) {
                particoe.setFree(true);
                return true;
            }
        }
        return false;
    }

    public static Simulador getInstance(){
        if (simulador == null){
            simulador = new Simulador(1000);
        }
        return simulador;
    }

    //Sei que não é bem de livro didático de padrões de projeto, mas achei que encaixaria bem
    public static void setInstance(int memoria){
        simulador = new Simulador(memoria);
    }
}
