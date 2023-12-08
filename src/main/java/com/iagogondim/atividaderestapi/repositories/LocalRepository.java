package com.iagogondim.atividaderestapi.repositories;

import com.iagogondim.atividaderestapi.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
}
