/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Specialite;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import repositories.SpecialiteRepository;

/**
 *
 * @author francoise
 */
@ControllerAdvice
public class SuperController {
   @Autowired
   SpecialiteRepository specialiteRepository; 
   @ModelAttribute("specialites")
    Collection<Specialite>findAllSpecialite()
    {
        Collection<Specialite> specialites=(Collection<Specialite>) specialiteRepository.findAll();
        return specialites;
        
    }
}
