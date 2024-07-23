package vitorcsouza.monitoracao_de_transacao_bancaria.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vitorcsouza.monitoracao_de_transacao_bancaria.dto.registroTransacaoDto;
import vitorcsouza.monitoracao_de_transacao_bancaria.model.Registro;
import vitorcsouza.monitoracao_de_transacao_bancaria.model.Transacao;
import vitorcsouza.monitoracao_de_transacao_bancaria.repository.transacaoRepository;
import vitorcsouza.monitoracao_de_transacao_bancaria.repository.registroRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class transacaoService {

    @Autowired
    private transacaoRepository repository;

    @Autowired
    private registroRepository registroRepository;

    public void salvarTransacoes(MultipartFile file) throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());

        List<Transacao> transacaoList = new ArrayList<>();
        boolean updated = false;
        LocalDateTime date = null;
        for (CSVRecord csvRecord : csvParser) {
            String[] transacaoDaVez = csvRecord.values();

            if (transacaoDaVez.length != 8) {
                continue;
            }

            boolean isValid = true;
            for (String item : transacaoDaVez) {
                if (item == null || item.isEmpty()) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                continue;
            }

            if (transacaoList.isEmpty()) {
                date = LocalDateTime.parse(transacaoDaVez[7]);
                transacaoList = repository.ListeTodasTransacoesDaData(date.toLocalDate());
            }
            if (LocalDateTime.parse(transacaoDaVez[7]).getDayOfYear() == date.getDayOfYear()) {
                Transacao transacao = new Transacao(transacaoDaVez);
                if (!transacaoList.contains(transacao)) {
                    transacaoList.add(transacao);
                    repository.save(transacao);
                    updated = true;
                }
            }
        }
        if (updated) {
            registro_de_transacao(date);
        }
    }

    private void registro_de_transacao(LocalDateTime date) {
        Registro registro = new Registro(date, LocalDateTime.now());

        registroRepository.save(registro);
    }

    public List<registroTransacaoDto> lerTransacoes() {
        List<Registro> registros = registroRepository.findAll();

        return registros.stream()
                .sorted(Comparator.comparing(Registro::getDate_transacoes).reversed())
                .map(registroTransacaoDto::new)
                .collect(Collectors.toList());
    }
}
