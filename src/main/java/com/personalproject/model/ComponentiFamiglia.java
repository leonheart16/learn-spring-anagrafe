package com.personalproject.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "componenti_famiglia")
public class ComponentiFamiglia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "soggetto_id", nullable = false)
	private Soggetto soggetto;

	@ManyToOne
	@JoinColumn(name = "famiglia_id", nullable = false)
	private Famiglia famiglia;

	@Column(nullable = false, length = 50)
	private String tipoParentela;

	@Column(nullable = false)
	private LocalDate dataAppartenenza;

	@Column
	private LocalDate dataUscita;

	// Costruttori
	public ComponentiFamiglia() {}

	public ComponentiFamiglia(Soggetto soggetto, Famiglia famiglia, String tipoParentela, LocalDate dataAppartenenza) {
		this.soggetto = soggetto;
		this.famiglia = famiglia;
		this.tipoParentela = tipoParentela;
		this.dataAppartenenza = dataAppartenenza;
	}

	// Getter e Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Soggetto getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(Soggetto soggetto) {
		this.soggetto = soggetto;
	}

	public Famiglia getFamiglia() {
		return famiglia;
	}

	public void setFamiglia(Famiglia famiglia) {
		this.famiglia = famiglia;
	}

	public String getTipoParentela() {
		return tipoParentela;
	}

	public void setTipoParentela(String tipoParentela) {
		this.tipoParentela = tipoParentela;
	}

	public LocalDate getDataAppartenenza() {
		return dataAppartenenza;
	}

	public void setDataAppartenenza(LocalDate dataAppartenenza) {
		this.dataAppartenenza = dataAppartenenza;
	}

	public LocalDate getDataUscita() {
		return dataUscita;
	}

	public void setDataUscita(LocalDate dataUscita) {
		this.dataUscita = dataUscita;
	}
}
