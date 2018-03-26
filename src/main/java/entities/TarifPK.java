/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author francoise
 */
@Embeddable
public class TarifPK implements Serializable {
    
    private Long idFormation;
    private Long idStatut;

    public Long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    public Long getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }
   
   
   /* @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarifPK)) {
            return false;
        }
        TarifPK other = (TarifPK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TarifPK[ id=" + id + " ]";
    }
*/
    public TarifPK(Long idFormation, Long idStatut) {
        this.idFormation = idFormation;
        this.idStatut = idStatut;
    }

    public TarifPK() {
    }
    
}
