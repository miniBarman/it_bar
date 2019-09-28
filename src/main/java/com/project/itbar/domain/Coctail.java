package com.project.itbar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
public class Coctail {
    @Id
    @SequenceGenerator(name = "coctailJpaSequence", sequenceName = "COCTAIL_JPA_SEQUENCE", allocationSize = 1, initialValue = 12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coctailJpaSequence")
    private BigInteger id;

    private String name;
    private String recipe;
    private String description;
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="coctail_id")
    @JsonIgnore
    private List<CoctailIngredient> coctailIngredients;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="label_id")
    @JsonIgnore
    private List<Label> labels;

    public Coctail() {
    }

    public Coctail(String name, String recipe, String description, User user) {
        this.author = user;
        this.recipe = recipe;
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

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}