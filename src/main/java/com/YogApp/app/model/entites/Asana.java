package com.YogApp.app.model.entites;

import com.YogApp.app.model.enums.Drishtis;
import com.YogApp.app.model.enums.TypeAsana;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Asana {
    @Id
    @Column(unique = true)
    private int id;
    private String name;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private Drishtis drishti;
    @Enumerated(EnumType.STRING)
    private TypeAsana typeAsana;
    private String urlImg;
    private String benefit;
    @ManyToMany(mappedBy = "asana")
    @JsonIgnore
    private List<User> user = new ArrayList<User>();

}
