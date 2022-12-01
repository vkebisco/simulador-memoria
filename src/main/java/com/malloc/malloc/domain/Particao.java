package com.malloc.malloc.domain;

public class Particao {

    private int enderecoDeInicio;
    private int tamanho;
    private boolean isFree;

    private Processo processo;
    private String duracao;

    public Particao(int enderecoDeInicio, int tamanho, boolean isFree, Processo processo) {
        this.enderecoDeInicio = enderecoDeInicio;
        this.tamanho = tamanho;
        this.isFree = isFree;
        this.processo = processo;
    }

    public Particao(int enderecoDeInicio, int tamanho, boolean isFree){
        this.enderecoDeInicio = enderecoDeInicio;
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

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
}
