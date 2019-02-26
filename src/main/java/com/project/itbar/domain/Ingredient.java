package com.project.itbar.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;

    private String name;
    private String description;
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private List<CoctailIngredient> coctailIngredients;

    @Enumerated(EnumType.STRING)
    private IngredientGroup ingredientGroup;

    public Ingredient() {
    }

    public Ingredient(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Ingredient(String name, String description, User user) {
        this.author = user;
        this.name = name;
        this.description = description;
    }

    public String getImgAddress(){return image != null ? image : "no_img.png"; }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "System";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<CoctailIngredient> getCoctailIngredients() {
        return coctailIngredients;
    }

    public void setCoctailIngredients(List<CoctailIngredient> coctailIngredients) {
        this.coctailIngredients = coctailIngredients;
    }

    public IngredientGroup getIngredientGroup() {
        return ingredientGroup;
    }

    public void setIngredientGroup(IngredientGroup ingredientGroup) {
        this.ingredientGroup = ingredientGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id );
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Ingredient ingr = (Ingredient) o;
        return Objects.equals( id, ingr.id );
    }
}