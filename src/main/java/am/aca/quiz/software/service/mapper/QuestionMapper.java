package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper implements MapEntityToDto<QuestionEntity, QuestionDto> {

    @Override
    public QuestionDto mapEntityToDto(QuestionEntity questionEntity) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(questionEntity.getId());
        questionDto.setCorrect_amount(questionEntity.getCorrect_amount());
        questionDto.setLevel(questionEntity.getLevel());
        questionDto.setPoints(questionEntity.getPoints());
        questionDto.setQuestion(questionEntity.getQuestion());

        return questionDto;
    }

    @Override
    public List<QuestionDto> mapEntitiesToDto(List<QuestionEntity> questionEntities) {
//        List<QuestionDto> questionDtos = new ArrayList<>();
//        for(QuestionEntity questionEntity : questionEntities) {
//            questionDtos.add(mapEntityToDto(questionEntity));
//        }
//        return questionDtos;

        return questionEntities
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());

    }
}
