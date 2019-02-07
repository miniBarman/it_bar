package com.project.itbar.domain;

import javax.persistence.*;
import java.math.BigInteger;

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

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "coctailIngredient_coctailId")
//    private List<CoctailIngredient> ingredientList;

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
}