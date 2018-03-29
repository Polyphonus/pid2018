/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Formation;
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

/**
 *
 * @author francoise
 */
@Controller
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class FormationController {
   @Autowired
   FormationRepository formationRepository; 
  
   @GetMapping("/ajoutFormation")
   public String getAjoutFormation()
   {
        return ("ajoutFormation");
   
   }  
   @PostMapping("/ajoutFormation")
        public ModelAndView postAjoutFormation(@ModelAttribute("newFormation")@Valid Formation newFormation, BindingResult bindingResult)
        {
            if(bindingResult.hasErrors())
            {
                return new ModelAndView("ajoutFormation");
            }
            System.out.println(newFormation.getSpecialite().getNomSpecialite());
           try{
            formationRepository.save(newFormation);
            }
            catch(Exception error)
            {
            return new ModelAndView("ajoutFormation","messages",newFormation.getTitre()+" existe déjà");
            }
       
           return new ModelAndView("ajoutFormation","messages",newFormation.getTitre()+" ajouté");
    }   
}
