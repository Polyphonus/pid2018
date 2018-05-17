/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Formation;
import entities.User;
import entities.UserRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositories.FormationRepository;
import repositories.UserRepository;
import repositories.UserRolesRepository;

/**
 *
 * @author francoise
 */
@Controller
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class ProfController {
   @Autowired
   FormationRepository formationRepository; 
   @Autowired
   UserRepository userRepository; 
   @Autowired
   UserRolesRepository userRolesRepository;
   List<String>lesFormationsId=new ArrayList();
   List<User>lesProfs=new ArrayList();
   @GetMapping("/admin/ajoutProf")
   public String getAjoutProf()
   {
        return ("ajoutProfesseur");
   
   }
    @PostMapping("/admin/ajoutProf")
   public ModelAndView postprofFormation(@ModelAttribute("user")User user, WebRequest wq)
   {
       
       User prof=userRepository.findOne(Long.valueOf(wq.getParameter("prof")));
       String message="Professeur ajouté et associé aux cours sélectionnés";
      
       
       for (String formationId:lesFormationsId)
       {
         Formation formation=formationRepository.findOne(Long.valueOf(formationId));
         //prof.getFormations().add(formation);
         formation.getProfesseurs().add(prof);
        try{
           
           formationRepository.save(formation);
        }
        catch(Exception error)
            {
             message="problème association " + formation.getTitre();
           
            }
      
        
            
            
           // return new ModelAndView("ajoutProfesseur","message",message);
            }
       
           
            UserRole newUserRole=new UserRole();
            newUserRole.setUserid(prof.getUserId());
            newUserRole.setRole("ROLE_PROF");
            userRolesRepository.save(newUserRole);
            lesFormationsId.clear();
            return new ModelAndView("ajoutProfesseur","message",message);
           
       
   
   } 
    @InitBinder()
    protected void initBinder(final WebDataBinder binder) 
    {         
        System.out.println("initbinder"); 

        binder.registerCustomEditor(List.class,"formations",new CustomCollectionEditor(Set.class){
  
            @Override
            protected Object convertElement(Object element) {
            lesFormationsId.add(element.toString());
          
           System.out.println("formation"+ element.toString());
            return element;
        }
    });
    }
}