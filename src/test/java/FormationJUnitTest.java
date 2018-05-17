/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author francoise
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
import org.springframework.security.test.context.support.WithMockUser;
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
public class FormationJUnitTest {
   @Autowired
    private MockMvc mvc;
@Test
@WithMockUser(username="admin",roles={"ADMIN"})
 public void test() throws Exception {
    //test get 
    mvc.perform(MockMvcRequestBuilders.get("/admin/ajoutFormation").accept(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(view().name("ajoutFormation"));
}
}
