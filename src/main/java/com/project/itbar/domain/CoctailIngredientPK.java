package com.project.itbar.domain;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CoctailIngredientPK implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    private Coctail coctail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    public CoctailIngredientPK() {
    }

    public CoctailIngredientPK(Coctail coctail, Ingredient ingredient) {
        this.coctail = coctail;
        this.ingredient = ingredient;
    }

    @Override
    public int hashCode() {
        return Objects.hash( coctail, ingredient );
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        CoctailIngredientPK pk = (CoctailIngredientPK) o;
        return Objects.equals( coctail, pk.coctail ) &&
                Objects.equals( ingredient, pk.ingredient );
    }
}