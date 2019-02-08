package com.project.itbar.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coctail_ingredient")
public class CoctailIngredient {

    @EmbeddedId
    CoctailIngredientPK coctailIngredientPK;

    float value;

}
