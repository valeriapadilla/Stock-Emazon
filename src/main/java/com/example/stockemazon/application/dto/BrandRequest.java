package com.example.stockemazon.application.dto;

import com.example.stockemazon.application.util.ApplicationConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BrandRequest {
    @NotNull(message = ApplicationConstant.BRAND_NAME_NULL_EXCEPTION)
    @Size(min = ApplicationConstant.BRAND_MIN_CHARACTERS_NAME, max = ApplicationConstant.BRAND_MAX_CHARACTERS_NAME, message = ApplicationConstant.BRAND_NAME_SIZE_EXCEPTION)
    private String name;

    @NotNull(message = ApplicationConstant.BRAND_DESCRIPTION_NULL_EXCEPTION)
    @Size(min = ApplicationConstant.BRAND_MIN_CHARACTERS_DESCRIPTION, max = ApplicationConstant.BRAND_MAX_CHARACTERS_DESCRIPTION, message = ApplicationConstant.BRAND_DESCRIPTION_SIZE_EXCEPTION)
    private String description;

    public BrandRequest(String name,String description) {
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
