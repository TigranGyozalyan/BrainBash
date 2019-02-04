package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubCategoryMapper implements MapEntityToDto<SubCategoryEntity, SubCategoryDto> {
    private CategoryMapper categoryMapper;

    public SubCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public SubCategoryDto mapEntityToDto(SubCategoryEntity subCategory) {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setId(subCategory.getId());
        subCategoryDto.setTypeName(subCategory.getTypeName());
        subCategoryDto.setCategoryDto(subCategory.getCategory().getId());

        return subCategoryDto;
    }

    @Override
    public List<SubCategoryDto> mapEntitiesToDto(List<SubCategoryEntity> subCategoryEntityList) {
//        List<SubCategoryDto> subCategoryDtoList = new ArrayList<>();
//        for (SubCategoryEntity subCategoryEntity : subCategoryEntityList) {
//            subCategoryDtoList.add(mapEntityToDto(subCategoryEntity));
//        }
//        return subCategoryDtoList;

        return subCategoryEntityList
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());

    }
}
