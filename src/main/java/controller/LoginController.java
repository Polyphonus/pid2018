/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.UserRole;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
//import Entities.Client;
import entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import Repositories.ClientRepository;
import repositories.UserRepository;
import repositories.UserRolesRepository;


/**
 *
 * @author francoise
 */
@Controller
@EntityScan("entities")
@EnableJpaRepositories("repositories")
//@ComponentScan("components")
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRolesRepository userRolesRepository;
   
    @ModelAttribute("user")
    public User newUser()
    {
        
         return new User();
    }
   @GetMapping("/login")
    public String getLogin()
    {
        return ("login");
    }
    @PostMapping("/login")
    public String postLoginPost()
    {
      
           return "redirect:/";
    } 
    @GetMapping("/logout")
    public String getLogout(HttpSession session)

    {
       session.invalidate();
       return "redirect:/";
    }
    @GetMapping("/inscription")
    public String getInscription()
    {
        return ("inscription");
    }
    @PostMapping("/inscription")
    public String postInscription(@ModelAttribute("user")@Valid User user,BindingResult result)
    {
    /*
    BindingResult interface qui permet d'appliquer un validateur et de lier les résultats avec les vues
    */
       if (result.hasErrors())
       {
           return "inscription";
       }
        user.setEnabled(1);
        String password=user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        UserRole userRole=new UserRole();
        userRole.setUserid(user.getUserid());
        userRole.setRole("ROLE_USER");
        userRolesRepository.save(userRole);
        return "index";
 }
    
}
