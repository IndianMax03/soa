package com.brigada.tickets_ejb.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterCriterion {

    private String fieldName;
    private String filterMode;
    private String value;

}
