package com.personalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalproject.model.StatoAnagrafico;
import com.personalproject.repository.StatoAnagraficoRepository;

@Service
public class StatoAnagraficoService {
  @Autowired
  private StatoAnagraficoRepository statoAnagraficoRepository;
  
  public StatoAnagrafico cerca_codice(String codice) {
    return statoAnagraficoRepository.findByCodice(codice);
  }

  public List<StatoAnagrafico> getAll() {
    return statoAnagraficoRepository.findAll();
  }
  
}
