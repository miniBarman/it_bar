package com.project.itbar.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coctail_ingredient")
public class CoctailIngredient {

    @EmbeddedId
    CoctailIngredientPK coctailIngredientPK;

    float volume;

    public CoctailIngredientPK getCoctailIngredientPK() {
        return coctailIngredientPK;
    }

    public void setCoctailIngredientPK(CoctailIngredientPK coctailIngredientPK) {
        this.coctailIngredientPK = coctailIngredientPK;
    }

    public Coctail getCoctail (){
        return coctailIngredientPK.getCoctail();
    }

    public Ingredient getIngredient (){
        return coctailIngredientPK.getIngredient();
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
