package com.YogApp.app.model.entites;

import com.YogApp.app.model.enums.Drishtis;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Asana {
    private int id;
    private String name;
    private String descrption;
    @Enumerated(EnumType.STRING)
    private Drishtis drishti;
    private String benefit;

    @ManyToMany(mappedBy = "asana")
    private User user;

}
