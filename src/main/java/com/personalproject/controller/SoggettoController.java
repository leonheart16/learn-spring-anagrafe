package com.personalproject.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.personalproject.model.Email;
import com.personalproject.model.Soggetto;
import com.personalproject.model.StatoAnagrafico;
import com.personalproject.service.EmailService;
import com.personalproject.service.SoggettoService;
import com.personalproject.service.StatoAnagraficoService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/soggetti")
public class SoggettoController {

	@Autowired
	private final SoggettoService soggettoService;

	@Autowired
	private final StatoAnagraficoService statoAnagraficoService;

	@Autowired
	private final EmailService emailService;

	public SoggettoController(SoggettoService soggettoService, StatoAnagraficoService statoAnagraficoService, EmailService emailService) {
		this.soggettoService = soggettoService;
		this.statoAnagraficoService = statoAnagraficoService;
		this.emailService = emailService;
	}

	@GetMapping()
	public String soggetti(Model model) {
		model.addAttribute("soggetti", soggettoService.getAllSoggetti());
		return "soggetti";
	}
	
	// mapping /soggetti/nuovo per inserire un nuovo soggetto
	@GetMapping("/nuovo")
	public String nuovoSoggetto(Model model) {
		model.addAttribute("soggetto", new Soggetto());
		model.addAttribute("statiAnagrafici", statoAnagraficoService.getAll());
		return "soggetto_form";
	}

	// mapping /soggetti/nuovo/modifica/{id} per modificare un soggetto esistente
	@GetMapping("modifica/{id}")
	public String modificaSoggetto(@PathVariable("id") Long id, Model model) {
		Soggetto soggetto = soggettoService.trovaSoggetto(id);
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dataNascitaFormatted = soggetto.getDataNascita().format(dateformatter);
		dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dataAggiornamentoFormatted = soggetto.getEmail().getDataAggiornamento().format(dateformatter);
		model.addAttribute("dataNascitaFormatted", dataNascitaFormatted);
		model.addAttribute("dataAggiornamentoFormatted", dataAggiornamentoFormatted);
		model.addAttribute("statiAnagrafici", statoAnagraficoService.getAll());
		model.addAttribute("soggetto", soggetto);
		return "modifica_soggetto";
	}

	@PostMapping
	public String creaSoggetto(
		@ModelAttribute("soggetto") @Validated Soggetto soggetto,
		BindingResult bindingResult,
		@RequestParam(value = "indirizzoEmail", required = false) String indirizzoEmail,
    @RequestParam(value = "tipoEmail", required = false) String tipoEmail,
		@RequestParam(value = "statoAnagrafico", required = true) StatoAnagrafico statoAnagrafico,
		RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "soggetto_form";
		}

		if (statoAnagrafico == null || statoAnagrafico.getCodice().isEmpty()) {
			bindingResult.rejectValue("statoAnagrafico", "errore.statoAnagrafico", "Seleziona uno stato anagrafico valido.");
			return "soggetto_form";
	}

		if (indirizzoEmail != null && !indirizzoEmail.isEmpty()) {
			Email emailEntity = new Email();
			emailEntity.setIndirizzoEmail(indirizzoEmail);
			emailEntity.setTipoEmail(tipoEmail);
			emailService.salvaEmail(emailEntity);  // Metodo per salvare l'email nel DB
			soggetto.setEmail(emailEntity);
		}

		soggetto.setStatoAnagrafico(statoAnagrafico);

		// Salvataggio del soggetto nel database
		soggettoService.salvaSoggetto(soggetto);

		// Attributo per indicare l'avvenuto inserimento del soggetto
		redirectAttributes.addFlashAttribute("successMessage", "Soggetto inserito correttamente!");
		
		// Redirect o visualizzazione di una pagina di successo
		return "redirect:/soggetti/nuovo";  // ritorniamo alla pagina stessa
	}

	@PostMapping("/salva")
	public String salvaModificaSoggetto(
		@ModelAttribute("soggetto") @Validated Soggetto soggettoEsistente,
		BindingResult bindingResult,
		@RequestParam(value = "indirizzoEmail", required = false) String indirizzoEmail,
		@RequestParam(value = "tipoEmail", required = false) String tipoEmail,
		@RequestParam(value = "statoAnagrafico", required = true) StatoAnagrafico statoAnagrafico,
		RedirectAttributes redirectAttributes,
		Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			model.addAttribute("statiAnagrafici", statoAnagraficoService.getAll());
			return "modifica_soggetto";  // Ritorna alla form con errori
		}

		// Controllo stato anagrafico valido
		if (statoAnagrafico == null || statoAnagrafico.getCodice().isEmpty()) {
			bindingResult.rejectValue("statoAnagrafico", "errore.statoAnagrafico", "Seleziona uno stato anagrafico valido.");
			model.addAttribute("statiAnagrafici", statoAnagraficoService.getAll());
			return "modifica_soggetto";
		}

	 // Controllo e aggiornamento dell'email solo se è cambiata
	 Email emailEsistente = soggettoEsistente.getEmail();

	 if (indirizzoEmail != null && !indirizzoEmail.isEmpty()) {
		if (emailEsistente == null || 
				!emailEsistente.getIndirizzoEmail().equals(indirizzoEmail) || 
				!emailEsistente.getTipoEmail().equals(tipoEmail)) {

			if (emailEsistente == null) {
				// Non esiste proprio la mail associata, ne creo una nuova
				emailEsistente = new Email();
			}

			emailEsistente.setIndirizzoEmail(indirizzoEmail);
			emailEsistente.setTipoEmail(tipoEmail);
			emailService.salvaEmail(emailEsistente);			
		}
	 } else {
			 // Se non viene passata un'email, manteniamo quella esistente
			 emailEsistente = soggettoEsistente.getEmail();
	 }

	 	soggettoEsistente.setEmail(emailEsistente);

	 	soggettoEsistente.setStatoAnagrafico(statoAnagrafico);

		// Salvataggio nel database
		soggettoService.salvaSoggetto(soggettoEsistente);

		// Messaggio di conferma
		redirectAttributes.addFlashAttribute("successMessage", "Soggetto modificato con successo!");
		
		return "redirect:/soggetti";  // Reindirizza alla lista dei soggetti
	}

}
