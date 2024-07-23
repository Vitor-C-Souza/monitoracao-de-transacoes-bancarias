package vitorcsouza.monitoracao_de_transacao_bancaria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vitorcsouza.monitoracao_de_transacao_bancaria.dto.registroTransacaoDto;
import vitorcsouza.monitoracao_de_transacao_bancaria.service.transacaoService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/csv")
public class transacaoController {
    @Autowired
    private transacaoService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a CSV file!");
        }
        service.salvarTransacoes(file);

        return ResponseEntity.ok().body("CSV file uploaded and processed successfully!");
    }

    @GetMapping
    public ResponseEntity<List<registroTransacaoDto>> readTransacoes(){
        List<registroTransacaoDto> dto = service.lerTransacoes();

        return ResponseEntity.ok(dto);
    }
}
