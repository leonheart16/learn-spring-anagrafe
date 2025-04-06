package com.personalproject.enums;

public enum StatoAnagraficoEnum {
	RESIDENTE					(1, "RE", "Residente"),
	EMIGRATO					(2, "EM", "Emigrato"),
	IRREPERIBILE			(3, "IR", "Irreperibile"),
	DECEDUTO					(4, "DE", "Deceduto"),
	CANCALTRIMOT			(5, "CC", "Cancellato per Altri Motivi"),
	ISCRITTO_AIRE			(6, "AA", "Iscritto all'AIRE"),
	CANCELLATO_AIRE		(7, "CA", "Cancellato AIRE"),
	IRREPERIBILE_AIRE	(8, "IA", "Irreperibile AIRE");

	private final Integer id;
	private final String codice;
	private final String descrizione;

	StatoAnagraficoEnum(Integer id, String codice, String descrizione) {
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
