package com.personalproject.repository;

import com.personalproject.model.Soggetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoggettoRepository extends JpaRepository<Soggetto, Long> {
  long countByStatoAnagrafico_Codice(String codice);
  long countBySesso(Character sesso);
}

