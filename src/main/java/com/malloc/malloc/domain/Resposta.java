package com.malloc.malloc.domain;

import java.util.List;

public class Resposta {

    private Long duracaoEmNanoSegundos;

    private List<Particao> particaoList;
    private boolean sucesso;

    public Resposta() {
    }

    public Resposta(Long duracaoEmNanoSegundos, List<Particao> particaoList, boolean sucesso) {
        this.duracaoEmNanoSegundos = duracaoEmNanoSegundos;
        this.particaoList = particaoList;
        this.sucesso = sucesso;
    }

    public List<Particao> getParticaoList() {
        return particaoList;
    }

    public void setParticaoList(List<Particao> particaoList) {
        this.particaoList = particaoList;
    }

    public Long getDuracaoEmNanoSegundos() {
        return duracaoEmNanoSegundos;
    }

    public void setDuracaoEmNanoSegundos(Long duracaoEmNanoSegundos) {
        this.duracaoEmNanoSegundos = duracaoEmNanoSegundos;
    }


    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }
}
