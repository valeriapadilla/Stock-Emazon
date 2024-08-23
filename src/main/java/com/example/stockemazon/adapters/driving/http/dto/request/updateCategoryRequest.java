package com.example.stockemazon.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class updateCategoryRequest {
    private final long id;
    private final String name;
    private final String description;




}
