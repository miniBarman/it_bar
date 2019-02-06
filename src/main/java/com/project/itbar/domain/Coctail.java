package com.project.itbar.domain;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Entity
public class Coctail {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Coctail() {
    }

    public Coctail(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Coctail(String name, String description, User user) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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