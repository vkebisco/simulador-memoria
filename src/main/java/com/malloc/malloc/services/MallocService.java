package com.malloc.malloc.services;

import com.google.common.base.Stopwatch;
import com.malloc.malloc.diverse.Alocacao;
import com.malloc.malloc.diverse.Simulador;
import com.malloc.malloc.domain.Particao;
import com.malloc.malloc.domain.ParticoesToSet;
import com.malloc.malloc.domain.Processo;
import com.malloc.malloc.domain.Resposta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/malloc")
public class MallocService {

    private boolean iniciado = false;

    private Simulador simulador;

    @PostMapping
    @RequestMapping("/setParticoes")
    public ResponseEntity setParticoes(@RequestBody ParticoesToSet particoes){

        List<Particao> particaoList = new ArrayList<>();

        int inicio = 0;
        for (int i : particoes.getParticoes()){
            particaoList.add(new Particao(inicio, i, true));
            inicio = inicio + i;
        }

        Alocacao tipo = null;

        switch (particoes.getTipoAlocacao()){
            case 1:
                tipo = Alocacao.BESTFIT;
                break;
            case 2:
                tipo = Alocacao.WORSTFIT;
                break;
            case 3:
                tipo = Alocacao.FIRSTFIT;
                break;
        }

        this.simulador = new Simulador(particoes.getTamanhoMemoria(), particaoList, tipo);

        this.iniciado = true;

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity showPartitions(){
        if (!iniciado){
            return notYetInit();
        }
        return ResponseEntity.status(200).body(getAll());
    }

    @GetMapping
    @RequestMapping("/reset")
    public ResponseEntity reset(){
        if (!iniciado){
            return notYetInit();
        }
        simulador.setParticoes(new ArrayList<>());
        this.iniciado = true;
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @RequestMapping("/alocar")
    public ResponseEntity alocar(@RequestBody Processo processo){
        if (!iniciado){
            return notYetInit();
        }

        Stopwatch timer = Stopwatch.createStarted();

        boolean success = simulador.alocar(processo);

        long elapsed = timer.stop().elapsed(TimeUnit.NANOSECONDS);

        var res = new Resposta(elapsed, simulador.getParticoes(), success);

        return ResponseEntity.ok(res);
    }

    @PostMapping
    @RequestMapping("/desalocar")
    public ResponseEntity desalocar(@RequestBody int index){
        if (!iniciado){
            return notYetInit();
        }
        return ResponseEntity.ok(simulador.desalocar(index));
    }

    private List<Particao> getAll(){
        return this.simulador.getParticoes();
    }

    private ResponseEntity notYetInit(){
        return ResponseEntity.status(400).body("{\"mensagem\":\"particoes ainda não foram setadas\"}");
    }
}

