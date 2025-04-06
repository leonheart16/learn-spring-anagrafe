package com.personalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalproject.model.Famiglia;

@Repository
public interface FamigliaRepository extends JpaRepository<Famiglia, Long>  {

}
