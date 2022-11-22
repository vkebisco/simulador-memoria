package com.malloc.malloc.domain;

import java.util.List;

public class Resposta {

    private Long duracaoEmNanoSegundos;

    private List<Memoria> memoriaList;

    public Resposta() {
    }

    public Resposta(Long duracaoEmNanoSegundos, List<Memoria> memoriaList) {
        this.duracaoEmNanoSegundos = duracaoEmNanoSegundos;
        this.memoriaList = memoriaList;
    }

    public Long getDuracaoEmNanoSegundos() {
        return duracaoEmNanoSegundos;
    }

    public void setDuracaoEmNanoSegundos(Long duracaoEmNanoSegundos) {
        this.duracaoEmNanoSegundos = duracaoEmNanoSegundos;
    }

    public List<Memoria> getMemoriaList() {
        return memoriaList;
    }

    public void setMemoriaList(List<Memoria> memoriaList) {
        this.memoriaList = memoriaList;
    }
}
