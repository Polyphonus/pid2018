/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author francoise
 */

@Entity
@Table(name = "users")
public class User implements Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)    
@Column(name="userid") private Long userId;
@Column(name = "username")
@NotNull
@Size(min = 1, message = "nom utilisateur  obligatoire")
private String userName;   
@Column(name = "password")
@NotNull
@Size(min = 1, message = "password  obligatoire")
private String password;   
@Column(name = "email")
private String email;
@Column(name ="enabled")
private int enabled;
public User(){}
public User(User user) {
    this.userId = user.userId;
    this.userName = user.userName;
    this.email = user.email;        
    this.password = user.password;
    this.enabled=user.enabled;        
}
public int getEnabled() {
    return enabled;
}
public void setEnabled(int enabled) {
    this.enabled = enabled;
}	
public Long getUserId() {
    return userId;
}
public void setUserId(Long userid) {
    this.userId = userid;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}

public String getUserName() {
    return userName;
}
public void setUserName(String userName) {
    this.userName = userName;
}
@ManyToMany (mappedBy = "professeurs")
private List <Formation>formations=new ArrayList();

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

}
