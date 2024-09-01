package com.example.stockemazon.application.dto;

import com.example.stockemazon.application.util.ApplicationConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryRequest {
    @NotNull(message = ApplicationConstant.CATEGORY_NAME_NULL_EXCEPTION)
    @Size(min = ApplicationConstant.CATEGORY_MIN_CHARACTERS_NAME, max = ApplicationConstant.CATEGORY_MAX_CHARACTERS_NAME, message = ApplicationConstant.CATEGORY_NAME_SIZE_EXCEPTION)
    private String name;

    @NotNull(message = ApplicationConstant.CATEGORY_DESCRIPTION_NULL_EXCEPTION)
    @Size(min = ApplicationConstant.CATEGORY_MIN_CHARACTERS_DESCRIPTION, max = ApplicationConstant.CATEGORY_MAX_CHARACTERS_DESCRIPTION, message = ApplicationConstant.CATEGORY_DESCRIPTION_SIZE_EXCEPTION)
    private String description;

    public CategoryRequest(String name,String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
