package com.brigada.general.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageMetadata implements Serializable {
    private static final long serialVersionUID = 7L;

    private int size;
    private int number;
    private long totalElements;
    private int totalPages;

}
