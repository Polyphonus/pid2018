/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Formation;
import entities.Specialite;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import repositories.FormationRepository;
import repositories.SpecialiteRepository;

/**
 *
 * @author francoise
 */
@ControllerAdvice
public class SuperController {
   @Autowired
   SpecialiteRepository specialiteRepository; 
   @Autowired
   FormationRepository formationRepository; 
   @ModelAttribute("specialites")
    Collection<Specialite>findAllSpecialite()
    {
        Collection<Specialite> specialites=(Collection<Specialite>) specialiteRepository.findAll();
        return specialites;
        
    }
    @ModelAttribute("newSpecialite")
    Specialite newSpecialite() 
    {
        return new Specialite();
    }
    @ModelAttribute("formations")
    Collection<Formation>findAllFormations()
    {
        Collection<Formation> formations=(Collection<Formation>) formationRepository.findAll();
        return formations;
        
    }
    @ModelAttribute("newFormation")
    Formation newFormation() 
    {
        return new Formation();
    }
}
