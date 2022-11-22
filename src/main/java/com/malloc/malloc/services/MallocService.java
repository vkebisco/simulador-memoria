package com.malloc.malloc.services;

import com.malloc.malloc.Simulador;
import com.malloc.malloc.domain.Memoria;
import com.malloc.malloc.domain.Particao;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/malloc")
public class MallocService {

    private boolean iniciado = false;
    private Simulador simulador;

    @PostMapping
    @RequestMapping("/init")
    public ResponseEntity customInit(@RequestParam("memorySize") int memorySize){
        if (!iniciado){
            this.simulador = new Simulador(memorySize);
            this.iniciado = true;
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("{\"mensagem\":\"iniciado\"}");
        }

        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{\"mensagem\":\"Simulador já foi iniciado\"}");
    }

    @PostMapping
    @RequestMapping("/bestFit")
    public ResponseEntity bestFit(@RequestParam("partitions") int partitions){
        if (!iniciado){
            init();
        }
        if (!alloc(partitions, Alocacao.BESTFIT)){
            return badResponse();
        }
        return ResponseEntity.ok(getAll());
    }

    @PostMapping
    @RequestMapping("/worstFit")
    public ResponseEntity worstFit(@RequestParam("partitions") int partitions){
        if (!iniciado){
            init();
        }
        if (!alloc(partitions, Alocacao.WORSTFIT)){
            return badResponse();
        }
        return ResponseEntity.ok(getAll());
    }

    @PostMapping
    @RequestMapping("/firstFit")
    public ResponseEntity firstFit(@RequestParam("partitions") int partitions){
        if (!iniciado){
            init();
        }
        if (!alloc(partitions, Alocacao.FIRSTFIT)){
            return badResponse();
        }

        return ResponseEntity.ok(getAll());
    }

    @GetMapping
    public ResponseEntity showPartitions(){
        if (!iniciado){
            init();
        }
        return ResponseEntity.status(200).body(getAll());
    }

    private List<Memoria> getAll(){
        return simulador.ShowPartitions();
    }

    private boolean alloc(int partitions, Alocacao alocacao) {
        Particao p = new Particao(partitions, false);

        int res  = -1;

        switch (alocacao){
            case BESTFIT:
                res = simulador.bestFit(p);
                break;
            case WORSTFIT:
                res = simulador.worstFit(p);
                break;
            case FIRSTFIT:
                res = simulador.firstFit(p);
                break;
        }

        return simulador.alloc(res, p, partitions);
    }

    private ResponseEntity badResponse(){
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON)
                .body("{\"mensagem\":\"Nenhum espaço livre satisfaz a alocação\"}");
    }

    private void init(){
        this.simulador = new Simulador(1000);
        this.iniciado = true;
    }
}

