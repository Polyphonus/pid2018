/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.lena.mavenprojectformations.FormationsApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 *
 * @author francoise
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=FormationsApplication.class, loader=AnnotationConfigWebContextLoader.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureMockMvc
//@PropertySource("classpath:application.properties")

public class SpecialiteJunitTest {
    @Autowired
    private MockMvc mvc;
    String nomSpecialite;
    String nomSpecialiteVide;
    public SpecialiteJunitTest() {
    }

    /**
     * Test of getSpecialites method, of class SpecialiteController.
     */
    @Before
    public void setUp() {
      
        nomSpecialite="Base de données";
        nomSpecialiteVide="";
                
    }
    @Test
     public void test() throws Exception {
         //test get 
        /* mvc.perform(MockMvcRequestBuilders.get("/ajoutSpecialite").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("ajoutSpecialite"));*/
        
        //test post sans erreur
        mvc.perform(MockMvcRequestBuilders.post("/ajoutSpecialite").accept(MediaType.APPLICATION_JSON_UTF8)
                    .param("nomSpecialite",nomSpecialite))
                    .andExpect(status().is(200))
                    .andExpect( redirectedUrl("/ajoutSpecialite"));
             
                   
        // test post avec doublon
        /*mvc.perform(MockMvcRequestBuilders.post("/ajoutSpecialite").accept(MediaType.APPLICATION_JSON_UTF8)
                    .param("nomSpecialite",nomSpecialite))
                    .andExpect(status().is(200))
                    .andExpect(view().name("ajoutSpecialite"))
                    .andExpect(model().attributeExists("message"));*/
        
        /* test post erreur longueur */
        /*mvc.perform(MockMvcRequestBuilders.post("/ajoutSpecialite").accept(MediaType.APPLICATION_JSON_UTF8)
                    .param("nomSpecialite",nomSpecialiteVide))
                    .andExpect(status().is(200))
                    .andExpect(view().name("ajoutSpecialite"))
                    .andExpect(model().attributeHasFieldErrors("newSpecialite"));*/
    }
}
