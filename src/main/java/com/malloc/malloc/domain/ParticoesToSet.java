package com.malloc.malloc.domain;

public class ParticoesToSet {
    private int[] particoes;
    private int tamanhoMemoria;
    private int tipoAlocacao;

    public ParticoesToSet() {
    }

    public ParticoesToSet(int[] particoes, int tamanhoMemoria, int tipoAlocacao) {
        this.particoes = particoes;
        this.tamanhoMemoria = tamanhoMemoria;
        this.tipoAlocacao = tipoAlocacao;
    }

    public int[] getParticoes() {
        return particoes;
    }

    public void setParticoes(int[] particoes) {
        this.particoes = particoes;
    }

    public int getTamanhoMemoria() {
        return tamanhoMemoria;
    }

    public void setTamanhoMemoria(int tamanhoMemoria) {
        this.tamanhoMemoria = tamanhoMemoria;
    }

    public int getTipoAlocacao() {
        return tipoAlocacao;
    }

    public void setTipoAlocacao(int tipoAlocacao) {
        this.tipoAlocacao = tipoAlocacao;
    }
}
