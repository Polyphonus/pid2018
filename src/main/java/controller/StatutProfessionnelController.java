/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author francoise
 */


import entities.Calendrier;
import entities.Specialite;
import entities.StatutProfessionnel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositories.CalendrierRepository;
import repositories.FormationRepository;
import repositories.StatutProfessionnelRepository;

/**
 *
 * @author francoise
 */
@Controller
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class StatutProfessionnelController {
   @Autowired
   StatutProfessionnelRepository statutProfRepository;
   @GetMapping("/admin/ajoutStatut")
   public String getAjoutStatut()
   {
        return ("ajoutStatut");
   
   } 
   @PostMapping("/admin/ajoutStatut")
        public ModelAndView postAjoutSpecialite(@ModelAttribute("newStatut")@Valid StatutProfessionnel newStatut, BindingResult bindingResult)
        {
            if(bindingResult.hasErrors())
            {
                return new ModelAndView("ajoutStatut");
            }
            try{
            statutProfRepository.save(newStatut);
            }
            catch(Exception error)
            {
            return new ModelAndView("ajoutStatut","message",newStatut.getStatut()+" existe déjà");
            }
            return new ModelAndView("redirect:/admin/ajoutStatut.htm");
         
        }
}