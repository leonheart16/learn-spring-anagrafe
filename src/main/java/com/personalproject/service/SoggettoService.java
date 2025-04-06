package com.personalproject.service;

import com.personalproject.model.Soggetto;
import com.personalproject.repository.SoggettoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoggettoService {
	@Autowired
	private SoggettoRepository soggettoRepository;

	public Soggetto salvaSoggetto(Soggetto soggetto) {
		return soggettoRepository.save(soggetto);
	}

	public Soggetto trovaSoggetto(Long id) {
		return soggettoRepository.findById(id).orElse(null);
	}

	public List<Soggetto> getAllSoggetti() {
		return soggettoRepository.findAll();
	}

	public long countAll() {
		return soggettoRepository.count();
	}

	public long countAttivi() {
		return soggettoRepository.countByStatoAnagrafico_Codice("RE");
	}
	
	public long countInattivi() {
		long totali = this.countAll();
		long attivi = this.countAttivi();
		return totali - attivi;
	}

	public long residentiMaschi() {
		return soggettoRepository.countBySesso('M');
	}

	public long residentiFemmine() {
		return soggettoRepository.countBySesso('F');
	}

}
