package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto {

    private Long id;
    private String question;
    private int correct_amount;
    private int points;
    private int level;


    public static QuestionDto mapEntityToDto (QuestionEntity questionEntity) {

        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(questionEntity.getId());
        questionDto.setCorrect_amount(questionEntity.getCorrect_amount());
        questionDto.setLevel(questionEntity.getLevel());
        questionDto.setPoints(questionEntity.getPoints());
        questionDto.setQuestion(questionEntity.getQuestion());

        return questionDto;
    }

    public static List<QuestionDto> mapEntityToDtos (List<QuestionEntity> questionEntities) {
        List<QuestionDto> questionDtos = new ArrayList<>();

        for(QuestionEntity questionEntity : questionEntities) {
            questionDtos.add(mapEntityToDto(questionEntity));
        }

        return questionDtos;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
