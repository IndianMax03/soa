package com.brigada.general.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesDto implements Serializable {
    private static final long serialVersionUID = 2L;

    private long id;
    private double x;
    private Float y;

}
