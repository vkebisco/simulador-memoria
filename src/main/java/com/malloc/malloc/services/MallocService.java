package com.malloc.malloc.services;

import com.google.common.base.Stopwatch;
import com.malloc.malloc.Simulador;
import com.malloc.malloc.domain.Memoria;
import com.malloc.malloc.domain.Particao;
import com.malloc.malloc.domain.Resposta;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/malloc")
public class MallocService {

    private boolean iniciado = false;

    @GetMapping
    @RequestMapping("/init")
    public ResponseEntity customInit(@RequestParam("memorySize") int memorySize){
        if (!iniciado){
            Simulador.setInstance(memorySize);
            this.iniciado = true;
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("{\"mensagem\":\"iniciado\"}");
        }

        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{\"mensagem\":\"Simulador já foi iniciado\"}");
    }

    @GetMapping
    @RequestMapping("/reset")
    public ResponseEntity reset(@RequestParam("memorySize") int memorySize){
        Simulador.setInstance(memorySize);
        this.iniciado = true;
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("{\"mensagem\":\"Reiniciado\"}");
    }

    @PostMapping
    @RequestMapping("/bestFit")
    public ResponseEntity bestFit(@RequestBody int[] partitions){
        if (!iniciado){
            init();
        }

        Stopwatch timer = Stopwatch.createStarted();

        for (int i: partitions) {
            if (!alloc(i, Alocacao.BESTFIT)){
                timer.stop();
                return badResponse();
            }
        }
        long elapsed = timer.stop().elapsed(TimeUnit.NANOSECONDS);

        return ResponseEntity.ok(getAll(elapsed));
    }


    @PostMapping
    @RequestMapping("/worstFit")
    public ResponseEntity worstFit(@RequestBody int[] partitions){
        if (!iniciado){
            init();
        }
        Stopwatch timer = Stopwatch.createStarted();
        for (int i: partitions) {
            if (!alloc(i, Alocacao.WORSTFIT)){
                timer.stop();
                return badResponse();
            }
        }
        long elapsed = timer.stop().elapsed(TimeUnit.NANOSECONDS);

        return ResponseEntity.ok(getAll(elapsed));
    }

    @PostMapping
    @RequestMapping("/firstFit")
    public ResponseEntity firstFit(@RequestBody int[] partitions){
        if (!iniciado){
            init();
        }
        Stopwatch timer = Stopwatch.createStarted();
        for (int i: partitions) {
            if (!alloc(i, Alocacao.FIRSTFIT)){
                timer.stop();
                return badResponse();
            }
        }
        long elapsed = timer.stop().elapsed(TimeUnit.NANOSECONDS);
        return ResponseEntity.ok(getAll(elapsed));
    }

    @GetMapping
    public ResponseEntity showPartitions(){
        if (!iniciado){
            init();
        }
        return ResponseEntity.status(200).body(getAll());
    }

    private List<Memoria> getAll(){
        return Simulador.getInstance().ShowPartitions();
    }
    private Resposta getAll(long duration){
        return new Resposta(duration, Simulador.getInstance().ShowPartitions());
    }

    private boolean alloc(int partitions, Alocacao alocacao) {
        Particao p = new Particao(partitions, false);

        int res  = -1;

        switch (alocacao){
            case BESTFIT:
                res = Simulador.getInstance().bestFit(p);
                break;
            case WORSTFIT:
                res = Simulador.getInstance().worstFit(p);
                break;
            case FIRSTFIT:
                res = Simulador.getInstance().firstFit(p);
                break;
        }

        return Simulador.getInstance().alloc(res, p, partitions);
    }

    private ResponseEntity badResponse(){
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON)
                .body("{\"mensagem\":\"Nenhum espaço livre satisfaz a alocação\"}");
    }

    private void init(){
        Simulador.setInstance(1000);
        this.iniciado = true;
    }
}

