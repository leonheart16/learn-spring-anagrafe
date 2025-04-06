package com.personalproject.config;

import com.personalproject.model.StatoAnagrafico;
import com.personalproject.enums.StatoAnagraficoEnum;
import com.personalproject.repository.StatoAnagraficoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StatoAnagraficoInitializer implements CommandLineRunner {

  private final StatoAnagraficoRepository repository;

  public StatoAnagraficoInitializer(StatoAnagraficoRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) {
    if (repository.count() == 0) {
      System.out.println("Inserimento stati anagrafici nel database...");

      List<StatoAnagrafico> stati = List.of(StatoAnagraficoEnum.values()).stream()
        .map(e -> new StatoAnagrafico(e.getId(), e.getCodice(), e.getDescrizione()))
        .toList();

      repository.saveAll(stati);
      System.out.println("Stato anagrafico popolato correttamente.");
    } else {
      System.out.println("La tabella statiAnagrafici è già popolata.");
    }
  }
}
