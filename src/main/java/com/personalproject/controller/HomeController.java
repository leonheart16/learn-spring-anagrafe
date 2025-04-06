package com.personalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.personalproject.service.SoggettoService;

import org.springframework.ui.Model;

@Controller
public class HomeController {

  private final SoggettoService soggettoService;

  @Autowired
  public HomeController(SoggettoService soggettoService) {
    this.soggettoService = soggettoService;
  }

  // mapping della root URL
  @GetMapping("/")
  public String homepage(Model model) {
    long totaleSoggetti = soggettoService.countAll();
    long attivi = soggettoService.countAttivi();
    long inattivi = soggettoService.countInattivi();
    long residentiMaschi = soggettoService.residentiMaschi();
    long residentiFemmine = soggettoService.residentiFemmine();

    model.addAttribute("totaleSoggetti", totaleSoggetti);
    model.addAttribute("attivi", attivi);    
    model.addAttribute("inattivi", inattivi);
    model.addAttribute("residentiMaschi", residentiMaschi);
    model.addAttribute("residentiFemmine", residentiFemmine);

    
    return "homepage";
  }
}
