package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper implements MapEntityToDto<CategoryEntity, CategoryDto> {
    @Override
    public CategoryDto mapEntityToDto(CategoryEntity categoryEntity) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setType(categoryEntity.getType());
        return categoryDto;
    }

    @Override
    public List<CategoryDto> mapEntitiesToDto(List<CategoryEntity> categoryEntityList) {
        if (categoryEntityList == null) {
            return null;
        }
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntityList) {
            categoryDtoList.add(mapEntityToDto(categoryEntity));
        }
        return categoryDtoList;
    }


}
