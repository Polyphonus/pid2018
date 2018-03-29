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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author francoise
 */
@Entity
@Table(
    uniqueConstraints={
        @UniqueConstraint(name="titre_unique", columnNames={"titre"})
    })
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length =50)
    @Size(min = 1,max=50, message = "Le titre de 50 caractères maximum est obligatoire" )
    private String titre;
    
    @Min(value = 1, message = "La durée est de minimum 1 jour" )
    private int duree;
    
    @Min(value = 1, message = "Le quota ne peut être nul" )
    private int quota;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Formation[ id=" + id + " ]";
    }

    public Formation() {
    }

    public Formation(String titre, int duree, int quota, Specialite specialite) {
        this.titre = titre;
        this.duree = duree;
        this.quota = quota;
        this.specialite = specialite;
    }

    
    @OneToOne
    @JoinColumn(nullable=false)
    private Specialite specialite;

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    @ManyToMany
    private List <User> professeurs=new ArrayList();

    public List<User> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(List<User> professeurs) {
        this.professeurs = professeurs;
    }

   
}
