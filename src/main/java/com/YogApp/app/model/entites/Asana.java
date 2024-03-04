package com.YogApp.app.model.entites;

import com.YogApp.app.model.enums.Drishtis;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Asana {
    @Id
    private int id;
    private String name;
    private String descrption;
    @Enumerated(EnumType.STRING)
    private Drishtis drishti;
    private String benefit;

    @ManyToMany(mappedBy = "asana")
    private List<User> user;

}
