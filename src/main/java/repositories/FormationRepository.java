/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Formation;
import entities.User;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francoise
 */
@Repository
public interface FormationRepository extends CrudRepository<Formation, Long>{
 
@Query("select a from Formation a where a.titre=?1")  
public Formation findByTitre(String titre);
}
