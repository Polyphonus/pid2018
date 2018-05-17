/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Formation;
import entities.StatutProfessionnel;
import entities.TarifFormation;
import entities.TarifPK;
import entities.User;
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
import repositories.StatutProfessionnelRepository;
import repositories.TarifRepository;
import repositories.UserRepository;

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
   @Autowired
   UserRepository userRepository; 
   @Autowired
   StatutProfessionnelRepository statutRepository; 
   @Autowired
   TarifRepository tarifRepository; 
   
   List<String>lesProfsId=new ArrayList();
   List<User>lesProfs=new ArrayList();
   
   @GetMapping("/admin/ajoutFormation")
   public String getAjoutFormation()
   {
        return ("ajoutFormation");
   
   }  
   @PostMapping("/admin/ajoutFormation")
        public ModelAndView postAjoutFormation(@ModelAttribute("newFormation")@Valid Formation newFormation, BindingResult bindingResult,WebRequest wq)
        {
            if(bindingResult.hasErrors())
            {
                return new ModelAndView("ajoutFormation");
            }
            System.out.println(newFormation.getSpecialite().getNomSpecialite());
           
           try{
            formationRepository.save(newFormation);
             Formation formation=formationRepository.findByTitre(newFormation.getTitre());
             for (StatutProfessionnel statut : statutRepository.findAll())
            { 
              TarifPK tarifPK=new TarifPK();
              tarifPK.setIdFormation(formation.getId());
              tarifPK.setIdStatut(statut.getId());
              String paramStatut=statut.getStatut();
              TarifFormation tarifFormation=new TarifFormation(tarifPK, Float.parseFloat(wq.getParameter(paramStatut)));
              tarifRepository.save(tarifFormation);
            }}
            catch(Exception error)
            {
            return new ModelAndView("ajoutFormation","message",newFormation.getTitre()+" existe déjà");
            }
       
           return new ModelAndView("ajoutFormation","message",newFormation.getTitre()+" ajouté");
    }  
   @GetMapping("/admin/profFormation")
   public String getprofFormation()
   {
        return ("profFormation");
   
   } 
   @PostMapping("/admin/profFormation")
   public ModelAndView postprofFormation(@ModelAttribute("newFormation")Formation newFormation)
   {
      Formation formation=formationRepository.findOne(Long.valueOf(newFormation.getId()));
       String message="";
       lesProfs.clear();
       
       for (String userId:lesProfsId)
       {
         User user=userRepository.findOne(Long.valueOf(userId));
         lesProfs.add(user);
         formation.getProfesseurs().add(user);
         
        }
       
        try{
            formationRepository.save(formation);
           
        }
            catch(Exception error)
            {
             message="formation "+ formation.getTitre()+ " est dejà associée aux professeurs suivants: ";
           
            for (User user: lesProfs)
            {
            message=message + " "+ user.getUserName();
            }  
            
            lesProfsId.clear();
            
            return new ModelAndView("profFormation","message",message);
            }
            
             message="formation "+ formation.getTitre()+ " a bien été associée aux professeurs sélectionnés ";
             for (User user: lesProfs)
            {
            message=message + " "+ user.getUserName();
            }  
             lesProfsId.clear();
            return new ModelAndView("profFormation","message",message);
           
       
   
   } 
    @InitBinder()
    protected void initBinder(final WebDataBinder binder) 
    {         
        System.out.println("initbinder"); 

        binder.registerCustomEditor(List.class,"professeurs",new CustomCollectionEditor(Set.class){
  
            @Override
            protected Object convertElement(Object element) {
            lesProfsId.add(element.toString());
          
           System.out.println("prof"+ element.toString());
            return element;
        }
    });
    }
}
