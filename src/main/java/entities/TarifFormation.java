/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author francoise
 */
@Entity
public class TarifFormation implements Serializable {
@EmbeddedId
    private TarifPK tarifPK;
    private float prix;

    public TarifPK getTarifPK() {
        return tarifPK;
    }

    public void setTarifPK(TarifPK tarifPK) {
        this.tarifPK = tarifPK;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public TarifFormation() {
    }

    public TarifFormation(TarifPK tarifPK, float prix) {
        this.tarifPK = tarifPK;
        this.prix = prix;
    }

   
}
