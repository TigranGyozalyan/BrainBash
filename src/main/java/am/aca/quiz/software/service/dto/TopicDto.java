package am.aca.quiz.software.service.dto;

public class TopicDto {
    private Long id;

    private String topicName;

    private SubCategoryDto subCategoryDto;

    public SubCategoryDto getSubCategoryDto() {
        return subCategoryDto;
    }

    public void setSubCategoryDto(SubCategoryDto subCategoryDto) {
        this.subCategoryDto = subCategoryDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
