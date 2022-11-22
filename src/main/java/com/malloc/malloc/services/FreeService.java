package com.malloc.malloc.services;

import com.malloc.malloc.Simulador;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/free")
public class FreeService {

    @GetMapping
    public ResponseEntity free(@RequestParam("startAddress") int startAddress){
        if (Simulador.getInstance().free(startAddress)){
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
                    .body(Simulador.getInstance().ShowPartitions());
        }
        return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON)
                .body("{\"mensagem\":\"Falha ao desalocar\"}");
    }
}
