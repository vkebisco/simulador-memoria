package com.malloc.malloc.domain;

import java.util.List;

public class Espaco {

    private List<Memoria> memorias;

    public Espaco() {
    }

    public Espaco(List<Memoria> memorias) {
        this.memorias = memorias;
    }

    public List<Memoria> getMemorias() {
        return memorias;
    }

    public void setMemorias(List<Memoria> memorias) {
        this.memorias = memorias;
    }
}
