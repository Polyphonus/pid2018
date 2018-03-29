/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Calendrier;
import entities.Formation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francoise
 */
@Repository
public interface CalendrierRepository extends CrudRepository<Calendrier, Long> {
    
}
