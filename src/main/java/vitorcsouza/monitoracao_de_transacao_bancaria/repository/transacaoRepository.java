package vitorcsouza.monitoracao_de_transacao_bancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vitorcsouza.monitoracao_de_transacao_bancaria.model.Transacao;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface transacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("SELECT t FROM Transacao t WHERE DATE(t.date_time) = :date")
    List<Transacao> ListeTodasTransacoesDaData(@Param("date") LocalDate date);
}
