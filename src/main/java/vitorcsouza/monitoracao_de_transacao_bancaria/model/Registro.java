package vitorcsouza.monitoracao_de_transacao_bancaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_de_transacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date_time_importacao;
    private LocalDate date_transacoes;

    public Registro(LocalDateTime date, LocalDateTime now) {
        this.date_time_importacao = now;
        this.date_transacoes = LocalDate.from(date);
    }
}
