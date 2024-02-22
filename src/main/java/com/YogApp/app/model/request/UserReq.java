package com.YogApp.app.model.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserReq {
    private String name;
    private String surname;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
}
