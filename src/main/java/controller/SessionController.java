/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Formation;
import entities.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import model.AjaxReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import repositories.FormationRepository;
import repositories.SpecialiteRepository;
import repositories.UserRepository;

/**
 *
 * @author francoise
 */
@Controller
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class SessionController {
   @Autowired
   SpecialiteRepository specialiteRepository; 
   @Autowired
   FormationRepository formationRepository; 
   @Autowired
   UserRepository userRepository; 
   
   @GetMapping("/admin/ajoutSession")
   public String getAjoutSession()
   {
        return ("ajoutSession");
   
   } 

    /**
     *
     * @param wq
     * @param map
     * @param formationId
     * @param errors
     * @return
     */
   @GetMapping("/admin/listProfFormation")
   public String getListProfFormationAjax(WebRequest wq,Model model )
   {
    System.out.println("admin");
    String formationId=wq.getParameter(";formationId");
   System.out.println("formationId" +formationId);
   Formation formation=formationRepository.findOne(Long.valueOf(formationId));
   List<User>formateurs=formation.getProfesseurs();
  
   
    model.addAttribute("formateurs",formateurs);
    return  "formateurs2 :: resultList";
           
      
   
   }
   @GetMapping("/admin/formateurs")
   public ModelAndView getFormateurs(WebRequest wq)
   {
       System.out.println("liste Formateurs:" +wq.getParameter("listeFormateurs"));
        return new ModelAndView   ("formateurs");   
   
   }
}