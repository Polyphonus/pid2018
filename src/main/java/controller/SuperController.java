/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Formation;
import entities.Session;
import entities.Specialite;
import entities.StatutProfessionnel;
import entities.User;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import repositories.FormationRepository;
import repositories.SpecialiteRepository;
import repositories.StatutProfessionnelRepository;
import repositories.UserRepository;
import repositories.UserRolesRepository;

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
   @Autowired
   UserRolesRepository userRoleRepository;
   @Autowired
   StatutProfessionnelRepository statutProfessionnelRepository;
   @Autowired
   UserRepository userRepository;
   
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
    
    @ModelAttribute("statuts")
    Collection<StatutProfessionnel>findAllStatut()
    {
        Collection<StatutProfessionnel> statuts=(Collection<StatutProfessionnel>) statutProfessionnelRepository.findAll();
        
        return statuts;
        
    }
    @ModelAttribute("newStatut")
    StatutProfessionnel newStatut() 
    {
        return new StatutProfessionnel();
    }
    @ModelAttribute("users")
    Collection<User>findAllUser()
    {
        Collection<User> users=(Collection<User>) userRoleRepository.findUsers();
        
        return users;
        
    }
    @ModelAttribute("newSession")
    Session newSession() 
    {
        return new Session();
    }
}
