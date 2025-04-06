package com.personalproject.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "emails")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 300)
	private String indirizzoEmail;

	@Column(nullable = true, length = 30)
	private String tipoEmail;

	@Column(nullable = true)
	private LocalDateTime dataAggiornamento;

	// Costruttori, getter e setter
	public Email() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndirizzoEmail() {
		return indirizzoEmail;
	}

	public void setIndirizzoEmail(String indirizzoEmail) {
		this.indirizzoEmail = indirizzoEmail;
	}

	public String getTipoEmail() {
		return tipoEmail;
	}

	public void setTipoEmail(String tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public LocalDateTime getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(LocalDateTime dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	@PrePersist
  public void prePersist() {
    if (dataAggiornamento == null) {
      dataAggiornamento = LocalDateTime.now();
    }
  }

  @PreUpdate
  public void preUpdate() {
    if (dataAggiornamento == null) {
      dataAggiornamento = LocalDateTime.now();
    }
  }
}
