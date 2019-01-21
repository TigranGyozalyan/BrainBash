package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.QuestionEntity;

public class QuestionDto {

    private Long id;
    private String question;
    private int correct_amount;
    private int points;
    private QuestionEntity.Level level;


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

    public int getCorrect_amount() {
        return correct_amount;
    }

    public void setCorrect_amount(int correct_amount) {
        this.correct_amount = correct_amount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public QuestionEntity.Level getLevel() {
        return level;
    }

    public void setLevel(QuestionEntity.Level level) {
        this.level = level;
    }
}
