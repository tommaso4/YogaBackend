package com.YogApp.app.model.entites;

import com.YogApp.app.model.enums.Drishtis;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private User user;

}
