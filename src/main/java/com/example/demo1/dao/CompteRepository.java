package com.example.demo1.dao;

import com.example.demo1.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Long> {

}

