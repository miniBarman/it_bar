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
    String unit;

    public CoctailIngredient() {
    }

    public CoctailIngredient(Coctail coctail, Ingredient ingredient, float volume, String unit) {
        this.coctailIngredientPK = new CoctailIngredientPK(coctail, ingredient);
        this.volume = volume;
        this.unit = unit;
    }

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
