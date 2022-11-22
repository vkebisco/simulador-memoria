package com.malloc.malloc.domain;

public class Particao {

    private int enderecoDeInicio;
    private int tamanho;

    private boolean isFree;


    public Particao(){
        this.enderecoDeInicio = -1;
        this.isFree = true;
        this.tamanho = 0;
    }

    public Particao(int enderecoDeInicio, int tamanho, boolean isFree){
        this.enderecoDeInicio = enderecoDeInicio;
        this.isFree = isFree;
        this.tamanho = tamanho;
    }
    public Particao(int tamanho, boolean isFree){
        this.enderecoDeInicio = -1;
        this.isFree = isFree;
        this.tamanho = tamanho;
    }

    public int getEnderecoDeInicio() {
        return enderecoDeInicio;
    }

    public void setEnderecoDeInicio(int enderecoDeInicio) {
        this.enderecoDeInicio = enderecoDeInicio;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void incrementarTamanho(int tamanho){
        this.tamanho += tamanho;
    }


}
