package com.example.stockemazon.domain.model;
import com.example.stockemazon.domain.exceptions.InvalidCategoryDescriptionException;
import com.example.stockemazon.domain.exceptions.InvalidCategoryNameException;
import com.example.stockemazon.domain.util.domainConstant;

public class Category {
    private Long id;
    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        if (name == null || name.length() > 50) {
            throw new InvalidCategoryNameException(domainConstant.INVALID_CATEGORY_NAME_CHARACTERS_EXCEPTION_MESSAGE);
        }
        if (description == null || description.length() > 90) {
            throw new InvalidCategoryDescriptionException(domainConstant.INVALID_CATEGORY_DESCRIPTION_CHARACTERS_EXCEPTION_MESSAGE);
        }
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
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
