package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryMapper implements MapEntityToDto<SubCategoryEntity, SubCategoryDto> {
    @Override
    public SubCategoryDto mapEntityToDto(SubCategoryEntity subCategory) {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setId(subCategory.getId());
        subCategoryDto.setTypeName(subCategory.getTypeName());

        return subCategoryDto;
    }

    @Override
    public List<SubCategoryDto> mapEntitiesToDto(List<SubCategoryEntity> subCategoryEntityList) {
        List<SubCategoryDto> subCategoryDtoList = new ArrayList<>();
        for (SubCategoryEntity subCategoryEntity : subCategoryEntityList) {
            subCategoryDtoList.add(mapEntityToDto(subCategoryEntity));
        }

        return subCategoryDtoList;
    }
}