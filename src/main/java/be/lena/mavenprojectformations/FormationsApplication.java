/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.lena.mavenprojectformations;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author francoise
 */
@SpringBootApplication
@ComponentScan("controller")

public class FormationsApplication {
    public static void main(String[] args) 
    {
    ApplicationContext ctx = SpringApplication.run(FormationsApplication.class, args);  
    }
}
