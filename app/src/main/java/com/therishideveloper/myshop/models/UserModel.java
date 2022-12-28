package com.therishideveloper.myshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Shuva Ranjan Rishi on 28,December,2022
 * BABL, Bangladesh,
 */

@Getter
@Setter
public class UserModel {

    public String id, name, email, password;

    public UserModel(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
