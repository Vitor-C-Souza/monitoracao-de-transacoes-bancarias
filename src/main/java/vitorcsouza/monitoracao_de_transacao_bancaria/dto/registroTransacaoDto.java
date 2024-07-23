package vitorcsouza.monitoracao_de_transacao_bancaria.dto;

import vitorcsouza.monitoracao_de_transacao_bancaria.model.Registro;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record registroTransacaoDto(
        LocalDateTime date_time_importacao,
        LocalDate date_transacoes
) {
    public registroTransacaoDto(Registro r) {
        this(
                r.getDate_time_importacao(),
                r.getDate_transacoes()
        );
    }
}
