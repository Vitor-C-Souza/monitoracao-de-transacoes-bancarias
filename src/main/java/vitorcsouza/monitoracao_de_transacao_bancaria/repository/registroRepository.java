package vitorcsouza.monitoracao_de_transacao_bancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitorcsouza.monitoracao_de_transacao_bancaria.model.Registro;

@Repository
public interface registroRepository extends JpaRepository<Registro, Long> {
}
