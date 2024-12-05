package com.brigada.general.model.dto;

import com.brigada.general.model.enums.VenueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueDto implements Serializable {
    private static final long serialVersionUID = 5L;

    private int id;
    private String name;
    private long capacity;
    private VenueType type;
    private AddressDto address;

}
