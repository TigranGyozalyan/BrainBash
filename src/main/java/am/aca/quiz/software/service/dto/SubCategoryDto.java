package am.aca.quiz.software.service.dto;

public class SubCategoryDto {
    private Long id;
    private String typeName;

    private Long categoryDtoId;

    public Long getCategoryDto() {
        return categoryDtoId;
    }

    public void setCategoryDto(Long categoryDto) {
        this.categoryDtoId = categoryDto;
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
