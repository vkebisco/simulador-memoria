package com.malloc.malloc.domain;

import java.util.List;

public class DesalocarProcessoDto {

    private int index;
    private List<Particao> particaoList;

    public DesalocarProcessoDto() {
    }

    public DesalocarProcessoDto(int index, List<Particao> particaoList) {
        this.index = index;
        this.particaoList = particaoList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Particao> getParticaoList() {
        return particaoList;
    }

    public void setParticaoList(List<Particao> particaoList) {
        this.particaoList = particaoList;
    }
}
