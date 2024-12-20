package com.brigada.general.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String zipCode;

}
