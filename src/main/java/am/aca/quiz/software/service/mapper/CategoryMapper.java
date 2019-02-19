package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
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
        return categoryEntityList
            .stream()
            .map(this::mapEntityToDto)
            .collect(Collectors.toList());

    }


}
