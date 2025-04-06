package com.personalproject.repository;

import com.personalproject.model.StatoAnagrafico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoAnagraficoRepository extends JpaRepository<StatoAnagrafico, Integer> {
  StatoAnagrafico findByCodice(String codice);
}
