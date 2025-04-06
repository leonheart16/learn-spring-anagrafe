package com.personalproject.enums;

public enum TipoFamigliaEnum {
  RESIDENTE (1,"R","Famiglia Residente"),
  AIRE      (2, "A", "Famiglia AIRE"),
  CONVIVENZA(3,"C","Convivenza Anagrafica");

  private final Integer id;
	private final String codice;
	private final String descrizione;

	TipoFamigliaEnum(Integer id, String codice, String descrizione) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public Integer getId() {
		return id;
	}

	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}
}