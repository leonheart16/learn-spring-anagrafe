package com.personalproject.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "soggetti")
public class Soggetto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
	private Long id;

	@Column(nullable = false, length = 100)
	private String cognome;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, length = 1)
	private Character sesso;

	@Column(nullable = false)
	private LocalDate dataNascita;
	
	@ManyToOne()
	@JoinColumn(name = "id_statoAnagrafico", referencedColumnName = "id", unique = false, nullable = false)
	private StatoAnagrafico statoAnagrafico;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "email_id", referencedColumnName = "id", unique = true, nullable = true)
	private Email email;

	@ManyToOne()
	@JoinColumn(name = "id_famiglia", referencedColumnName = "id", unique = false, nullable = true)
	private Famiglia famiglia;

	// Costruttori
	public Soggetto() {}

	// Getter e Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Character getSesso() {
		return sesso;
	}

	public void setSesso(Character sesso) {
		this.sesso = sesso;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
			this.email = email;
	}

	public StatoAnagrafico getStatoAnagrafico() {
		return statoAnagrafico;
	}

	public void setStatoAnagrafico(StatoAnagrafico statoAnagrafico) {
		this.statoAnagrafico = statoAnagrafico;
	}

}
