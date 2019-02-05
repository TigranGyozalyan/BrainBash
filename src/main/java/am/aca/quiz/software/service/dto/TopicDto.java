package am.aca.quiz.software.service.dto;

public class TopicDto {
    private Long id;

    private String topicName;

    private Long subCategoryDtoId;

    public Long getSubCategoryDto() {
        return subCategoryDtoId;
    }

    public void setSubCategoryDto(Long subCategoryDto) {
        this.subCategoryDtoId = subCategoryDto;
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
