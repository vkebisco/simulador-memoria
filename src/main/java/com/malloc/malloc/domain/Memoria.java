package com.malloc.malloc.domain;

public class Memoria {
    private int inicio;
    private int fim;
    private boolean isFree;

    public Memoria() {
    }

    public Memoria(int inicio, int fim, boolean isFree) {
        this.inicio = inicio;
        this.fim = fim;
        this.isFree = isFree;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getTamanho(){
        return fim - inicio + 1;
    }
}
