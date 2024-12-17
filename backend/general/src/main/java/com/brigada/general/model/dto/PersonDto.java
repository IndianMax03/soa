package com.brigada.general.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto implements Serializable {
    private static final long serialVersionUID = 3L;

    private long id;
    private String username;
    private String password;
    private double balance;

}
