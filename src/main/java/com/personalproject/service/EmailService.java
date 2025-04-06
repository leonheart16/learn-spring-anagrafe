package com.personalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalproject.model.Email;
import com.personalproject.repository.EmailRepository;

@Service
public class EmailService {
  @Autowired
  private EmailRepository emailRepository;

  public Email salvaEmail(Email email) {
    return emailRepository.save(email);
  }

  public Email trovaEmail(Long id) {
    return emailRepository.findById(id).orElse(null);
  }
  
}
