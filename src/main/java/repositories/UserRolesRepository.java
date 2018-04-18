/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.List;
import entities.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author francoise
 */

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	
@Query("select a.role from UserRole a, User b where b.userName=?1 and a.userid=b.userId")
public List<String> findRoleByUserName(String username); 
}
