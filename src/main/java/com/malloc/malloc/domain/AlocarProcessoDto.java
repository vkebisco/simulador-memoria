package com.malloc.malloc.domain;

import java.util.List;

public class AlocarProcessoDto {

    private Processo processo;
    private List<Particao> particaoList;

    public AlocarProcessoDto() {
    }

    public AlocarProcessoDto(Processo processo, List<Particao> particaoList) {
        this.processo = processo;
        this.particaoList = particaoList;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<Particao> getParticaoList() {
        return particaoList;
    }

    public void setParticaoList(List<Particao> particaoList) {
        this.particaoList = particaoList;
    }
}
