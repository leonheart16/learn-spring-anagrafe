package com.personalproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="statiAnagrafici")
public class StatoAnagrafico {
  @Id
  private Integer id;

  @Column(nullable = false, unique = true, length = 2)
  private String codice;

  @Column(nullable = false, unique = true, length = 100)
  private String descrizione;

  public StatoAnagrafico() {}

  public StatoAnagrafico(Integer id, String codice, String descrizione) {
    this.id = id;
    this.codice = codice;
    this.descrizione = descrizione;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCodice() {
    return codice;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }
  
}
