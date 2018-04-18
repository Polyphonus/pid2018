/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author francoise
 */
public interface UserRepository  extends CrudRepository<User, Long> {
 // méthode magique
  public User findByUserName(String username);
}
