package com.example.stockemazon.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class categoryResponse {
    private final Long id;
    private final String name;
    private final String description;
}

