/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Specialite;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import repositories.FormationRepository;
import repositories.SpecialiteRepository;

/**
 *
 * @author francoise
 */
@Controller
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class SpecialiteController {
   @Autowired
   SpecialiteRepository specialiteRepository; 
   @Autowired
   FormationRepository formationRepository; 
   
   @GetMapping("/formations")
   public String getSpecialites()
   {
        return ("listeSpecialites");
   
   } 
   @GetMapping("/admin/ajoutSpecialite")
   public String getAjoutSpecialite()
   {
        return ("ajoutSpecialite");
   
   }  
   @PostMapping("/admin/ajoutSpecialite")
        public ModelAndView postAjoutSpecialite(@ModelAttribute("newSpecialite")@Valid Specialite newSpecialite, BindingResult bindingResult)
        {
            if(bindingResult.hasErrors())
            {
                return new ModelAndView("ajoutSpecialite");
            }
            try{
            specialiteRepository.save(newSpecialite);
            }
            catch(Exception error)
            {
            return new ModelAndView("ajoutSpecialite","message",newSpecialite.getNomSpecialite()+" existe déjà");
            }
       
            return new ModelAndView("redirect:/admin/ajoutSpecialite.htm");
    }   
}
