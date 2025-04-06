package com.personalproject.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Model model) {
    // Ottieni il codice di stato dell'errore
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      int statusCode = Integer.parseInt(status.toString());

      // Esegui uno switch sul codice di stato per gestire i vari errori
      switch (statusCode) {
        case 404:
          model.addAttribute("errorType", "404");
          return "error-404";
        case 403:
          model.addAttribute("errorType", "403");
          return "error-forbidden";
        case 500:
          model.addAttribute("errorType", "500");
          return "error-500";
        default:
          model.addAttribute("errorType", String.valueOf(statusCode));
          return "errorPage";
      }
    }

    // restituisco la pagina errorPage per gestire l'errore generico
    return "errorPage";  // Default
  }
}
