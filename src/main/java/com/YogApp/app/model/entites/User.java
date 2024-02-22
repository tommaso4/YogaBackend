package com.YogApp.app.model.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",sequenceName = "user_sequence",allocationSize = 1)
    private int id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;

    //List<Pose> poses = new ArrayList<Pose>();
}
