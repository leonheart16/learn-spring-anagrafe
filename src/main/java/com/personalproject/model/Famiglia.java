package com.personalproject.model;

import java.time.LocalDate;

import com.personalproject.enums.TipoFamigliaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "famiglie", 
			uniqueConstraints = @UniqueConstraint(columnNames = {"codice","tipoFamiglia"})
)
public class Famiglia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
	private Long id;

	@Column(nullable = false, length = 10)
	private String codice;

	@Enumerated()
	@Column(nullable = false)
	private TipoFamigliaEnum tipoFamiglia;

	@Column(nullable = false)
	private LocalDate dataCostituzione;
	
	@Column(nullable = true)
	private LocalDate dataEliminazione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public TipoFamigliaEnum getTipoFamiglia() {
		return tipoFamiglia;
	}

	public void setTipoFamiglia(TipoFamigliaEnum tipoFamiglia) {
		this.tipoFamiglia = tipoFamiglia;
	}

	public LocalDate getDataCostituzione() {
		return dataCostituzione;
	}

	public void setDataCostituzione(LocalDate dataCostituzione) {
		this.dataCostituzione = dataCostituzione;
	}

	public LocalDate getDataEliminazione() {
		return dataEliminazione;
	}

	public void setDataEliminazione(LocalDate dataEliminazione) {
		this.dataEliminazione = dataEliminazione;
	}
}
