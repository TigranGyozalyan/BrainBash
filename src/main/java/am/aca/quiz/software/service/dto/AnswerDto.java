package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.AnswerEntity;

import java.util.ArrayList;
import java.util.List;

public class AnswerDto {

    private Long id;
    private String answer_text;
    private String description;
    private boolean is_correct;

    public static AnswerDto mapEntityToDto (AnswerEntity answerEntity) {
        AnswerDto answerDto = new AnswerDto();

        answerDto.setAnswer_text(answerEntity.getAnswer_text());
        answerDto.setDescription(answerEntity.getDescription());
        answerDto.setId(answerEntity.getId());
        answerDto.setIs_correct(answerEntity.isIs_correct());

        return answerDto;
    }


    public static List<AnswerDto> mapEntitiesToDto (List<AnswerEntity> answerEntities) {

        List<AnswerDto> answerDtos = new ArrayList<>();

        for (AnswerEntity answerEntity : answerEntities) {
            answerDtos.add(mapEntityToDto(answerEntity));
        }
        return answerDtos;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_dto) {
        this.answer_text = answer_dto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }
}
