/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Calendrier;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
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
    @PostMapping("/ajoutCalendrier")
    public ModelAndView postAjoutCalendrier(WebRequest wq)
    {
        String[] dateDebut =wq.getParameterValues("dateDebut");
        String[]dateFin=wq.getParameterValues("dateFin");
        SimpleDateFormat simpleDateFormat=new  SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        int cpt=0;
       
        try{
            Date dateCourante=simpleDateFormat.parse(dateDebut[0]);
           // dateCourante.setTime(23l);
            Date dateFinFormatee=simpleDateFormat.parse(dateFin[0]);
            System.out.println("date debut "+ dateCourante);
            System.out.println("date fin "+ dateFinFormatee);
            cal.setTime(dateCourante);
            cal.add(Calendar.DAY_OF_MONTH,1);
            dateCourante=cal.getTime();
            cal.setTime(dateFinFormatee);
            cal.add(Calendar.DAY_OF_MONTH,1);
            dateFinFormatee=cal.getTime();
            calendrierRepository.save(new Calendrier(dateCourante));
            while (dateCourante.before(dateFinFormatee))
                {
                cal.setTime(dateCourante);
                cal.add(Calendar.DAY_OF_MONTH,1);
                dateCourante=cal.getTime();
                System.out.println("date courante "+ dateCourante);
                calendrierRepository.save(new Calendrier(dateCourante));
                cpt++;
                System.out.println("cpt "+ cpt);
                }
        }
        catch(Exception e){
        
        }
       
       
         
       
            return new ModelAndView("redirect:/ajoutCalendrier.htm");
        
       
   
    }     
}
