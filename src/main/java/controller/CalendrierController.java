/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import repositories.CalendrierRepository;
import repositories.FormationRepository;

/**
 *
 * @author francoise
 */
@Controller
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class CalendrierController {
    @Autowired
    CalendrierRepository calendrierRepository; 
  
    @GetMapping("/ajoutCalendrier")
    public String getAjoutCalendrier()
    {
        return ("ajoutCalendrier");
   
    }     
}
