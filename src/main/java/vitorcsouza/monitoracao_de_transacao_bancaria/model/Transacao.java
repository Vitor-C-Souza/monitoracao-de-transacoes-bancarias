package vitorcsouza.monitoracao_de_transacao_bancaria.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String banco_origem;
    private String agencia_origem;
    private String conta_origem;
    private String banco_destino;
    private String agencia_destino;
    private String conta_destino;
    private double valor;
    private LocalDateTime date_time;

    public Transacao(String[] transacoes) {
        this.banco_origem = transacoes[0];
        this.agencia_origem = transacoes[1];
        this.conta_origem = transacoes[2];
        this.banco_destino = transacoes[3];
        this.agencia_destino = transacoes[4];
        this.conta_destino = transacoes[5];
        this.valor = Double.parseDouble(transacoes[6]);
        this.date_time = LocalDateTime.parse(transacoes[7]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Double.compare(transacao.valor, valor) == 0 &&
                Objects.equals(banco_origem, transacao.banco_origem) &&
                Objects.equals(agencia_origem, transacao.agencia_origem) &&
                Objects.equals(conta_origem, transacao.conta_origem) &&
                Objects.equals(banco_destino, transacao.banco_destino) &&
                Objects.equals(agencia_destino, transacao.agencia_destino) &&
                Objects.equals(conta_destino, transacao.conta_destino) &&
                Objects.equals(date_time, transacao.date_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banco_origem, agencia_origem, conta_origem, banco_destino, agencia_destino, conta_destino, valor, date_time);
    }
}
