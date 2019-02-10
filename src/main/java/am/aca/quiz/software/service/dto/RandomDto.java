package am.aca.quiz.software.service.dto;

import java.util.List;

public class RandomDto {

    private String name;
    private String description;
    private String level;
    private Long questionNumber;
    private Long duration;

    private List<Long> topicId;

    public List<Long> getTopicId() {
        return topicId;
    }

    public void setTopicId(List<Long> topicId) {
        this.topicId = topicId;
    }

    public Long getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Long questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
