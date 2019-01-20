package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    private Long id;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
