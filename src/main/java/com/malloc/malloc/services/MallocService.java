package com.malloc.malloc.services;

import com.google.common.base.Stopwatch;
import com.malloc.malloc.diverse.Alocacao;
import com.malloc.malloc.diverse.Simulador;
import com.malloc.malloc.domain.*;
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


    @RequestMapping(value = "/setParticoes", method = RequestMethod.POST)
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

        return ResponseEntity.status(201).body(getAll());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity showPartitions(){
        if (!iniciado){
            return notYetInit();
        }
        return ResponseEntity.status(200).body(getAll());
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ResponseEntity reset(){
        if (!iniciado){
            return notYetInit();
        }
        simulador.setParticoes(new ArrayList<>());
        this.iniciado = false;
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/alocar", method = RequestMethod.POST)
    public ResponseEntity alocar(@RequestBody AlocarProcessoDto dto){
        if (!iniciado){
            return notYetInit();
        }

        Stopwatch timer = Stopwatch.createStarted();

        var tupla = simulador.alocar(dto.getProcesso());

        boolean success = (boolean) tupla[0];

        long elapsed = timer.stop().elapsed(TimeUnit.NANOSECONDS);

        atualizaDuracao(dto.getParticaoList(), (int) tupla[1], dto.getProcesso().getDuracao());

        return ResponseEntity.ok(new Resposta(elapsed, simulador.getParticoes(), success));
    }

    @RequestMapping(value = "/desalocar", method = RequestMethod.POST)
    public ResponseEntity desalocar(@RequestBody DesalocarProcessoDto dto){
        if (!iniciado){
            return notYetInit();
        }
        simulador.desalocar(dto.getIndex());

        atualizaDuracao(dto.getParticaoList());

        return ResponseEntity.ok(getAll());
    }

    private List<Particao> getAll(){
        return this.simulador.getParticoes();
    }

    private ResponseEntity notYetInit(){
        return ResponseEntity.status(400).body("{\"mensagem\":\"particoes ainda nao foram setadas\"}");
    }

    private void atualizaDuracao(List<Particao> list){
        int count = 0;
        Particao p = null;
        String duracaoToset = null;

        for (Particao particaoDto : list){

            duracaoToset = particaoDto.getProcesso().getDuracao();

            p = simulador.getParticoes().get(count);
            if (p.getProcesso() != null){
                p.getProcesso().setDuracao(duracaoToset);
            }
            count++;
        }
    }

    private void atualizaDuracao(List<Particao> list, int index, String duracao){
        int count = 0;
        Particao p = null;
        String duracaoToset = null;

        for (Particao particaoDto : list){

            p = simulador.getParticoes().get(count);
            if (count == index){
                p.getProcesso().setDuracao(duracao);
            }else {
                duracaoToset = particaoDto.getProcesso().getDuracao();

                if (p.getProcesso() != null){
                    p.getProcesso().setDuracao(duracaoToset);
                }
            }

            count++;
        }
    }


}

