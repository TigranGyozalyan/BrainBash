package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.service.dto.AnswerDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnswerMapper implements MapEntityToDto<AnswerEntity, AnswerDto> {

    @Override
    public AnswerDto mapEntityToDto (AnswerEntity answerEntity) {
        AnswerDto answerDto = new AnswerDto();

        answerDto.setAnswer(answerEntity.getAnswer_text());
        answerDto.setDescription(answerEntity.getDescription());
        answerDto.setId(answerEntity.getId());
        answerDto.setCorrect(answerEntity.isIs_correct());
        answerDto.setQuestionId(answerEntity.getQuestionEntity().getId());
        return answerDto;
    }

    @Override
    public  List<AnswerDto> mapEntitiesToDto (List<AnswerEntity> answerEntities) {
        //IF ?
       return answerEntities
                .stream()
                .map(this::mapEntityToDto) // Same as i->mapEntityDto(i)
                .collect(Collectors.toList());
    }
}
