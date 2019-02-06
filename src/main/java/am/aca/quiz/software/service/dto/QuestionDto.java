package am.aca.quiz.software.service.dto;


import java.util.List;
import java.util.Objects;

public class QuestionDto {
    private Long id;
    private Long topicId;
    private String question;
    private int points;
    private String level;
    private List<AnswerDto> answerDtoList;


    public Long getTopicId() {
        return topicId;
    }


    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public List<AnswerDto> getAnswerDtoList() {
        return answerDtoList;
    }

    public void setAnswerDtoList(List<AnswerDto> answerDtoList) {
        this.answerDtoList = answerDtoList;
    }


    private TopicDto topicDto;

    public TopicDto getTopicDto() {
        return topicDto;
    }

    public void setTopicDto(TopicDto topicDto) {
        this.topicDto = topicDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return points == that.points &&
                Objects.equals(id, that.id) &&
                Objects.equals(question, that.question) &&
                level.equals(that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, points, level);
    }
}
