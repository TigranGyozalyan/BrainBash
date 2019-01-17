package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.entity.SubCategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryDto {
    private Long id;
    private String typeName;

    public static SubCategoryDto mapEntityToDto(SubCategoryEntity subCategory) {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setId(subCategory.getId());
        subCategoryDto.setTypeName(subCategory.getTypeName());

        return subCategoryDto;
    }

    public static List<SubCategoryDto> mapEntityToDtos(List<SubCategoryEntity> subCategoryEntityList) {
        List<SubCategoryDto> subCategoryDtoList = new ArrayList<>();
        for (SubCategoryEntity subCategoryEntity : subCategoryEntityList) {
            subCategoryDtoList.add(mapEntityToDto(subCategoryEntity));
        }

        return subCategoryDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


}