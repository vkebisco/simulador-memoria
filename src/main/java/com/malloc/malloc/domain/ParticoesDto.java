package com.malloc.malloc.domain;

public class ParticoesDto {
    private int[] particoes;
    private int tipoAlocacao;

    public ParticoesDto() {
    }

    public ParticoesDto(int[] particoes, int tipoAlocacao) {
        this.particoes = particoes;
        this.tipoAlocacao = tipoAlocacao;
    }

    public int[] getParticoes() {
        return particoes;
    }

    public void setParticoes(int[] particoes) {
        this.particoes = particoes;
    }

    public int getTipoAlocacao() {
        return tipoAlocacao;
    }

    public void setTipoAlocacao(int tipoAlocacao) {
        this.tipoAlocacao = tipoAlocacao;
    }
}
