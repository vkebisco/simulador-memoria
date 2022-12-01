package com.malloc.malloc.domain;

public class Processo {
    private String nome;
    private int tamanho;
    private long duracao;

    public Processo() {
    }

    public Processo(String nome, int tamanho, long duracao) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }
}