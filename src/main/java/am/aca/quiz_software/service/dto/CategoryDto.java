package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    private Long id;

    private String type;

    public static CategoryDto mapEntityToDto(CategoryEntity categoryEntity){
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setType(categoryEntity.getType());
        return categoryDto;
    }

    public static List<CategoryDto> mapEntityToDtos(List<CategoryEntity> categoryEntityList){
        if(categoryEntityList==null){
            return null;
        }
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        for(CategoryEntity categoryEntity: categoryEntityList){
            categoryDtoList.add(mapEntityToDto(categoryEntity));
        }
        return categoryDtoList;

    }

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
