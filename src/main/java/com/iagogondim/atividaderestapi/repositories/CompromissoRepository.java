package com.iagogondim.atividaderestapi.repositories;

import com.iagogondim.atividaderestapi.entities.Compromisso;
import com.iagogondim.atividaderestapi.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long> {
}
