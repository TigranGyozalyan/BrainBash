package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.enums.Level;

import java.util.Objects;

public class QuestionDto {

    private Long id;
    private String question;
    private int correct_amount;
    private int points;
    private Level level;


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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return correct_amount == that.correct_amount &&
                points == that.points &&
                Objects.equals(id, that.id) &&
                Objects.equals(question, that.question) &&
                level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, correct_amount, points, level);
    }
}
