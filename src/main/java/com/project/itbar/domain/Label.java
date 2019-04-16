package com.project.itbar.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class Label {

    @Id
    @SequenceGenerator(name = "labelJpaSequence", sequenceName = "LABEL_JPA_SEQUENCE", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "labelJpaSequence")
    private BigInteger id;

    private String name;

    public Label() {
    }

    public Label(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
