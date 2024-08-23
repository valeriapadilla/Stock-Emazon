package com.example.stockemazon.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class addCategoryRequest {
    private final String name;
    private final String description;
}
