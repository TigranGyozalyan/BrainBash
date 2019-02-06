package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.service.dto.AnswerDto;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.implementations.AnswerServiceImp;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper implements MapEntityToDto<QuestionEntity, QuestionDto> {

    private AnswerServiceImp answerServiceImp;

    private AnswerMapper answerMapper;

    public QuestionMapper(AnswerServiceImp answerServiceImp, AnswerMapper answerMapper) {
        this.answerServiceImp = answerServiceImp;
        this.answerMapper = answerMapper;
    }


    @Override
    public QuestionDto mapEntityToDto(QuestionEntity questionEntity) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(questionEntity.getId());
        questionDto.setLevel(questionEntity.getLevel().toString());
        questionDto.setPoints(questionEntity.getPoints());
        questionDto.setQuestion(questionEntity.getQuestion());
        questionDto.setTopicId(questionEntity.getTopicEntity().getId());

        List<AnswerDto> answerDtos = answerMapper
                .mapEntitiesToDto(answerServiceImp
                        .getAnswerEntitiesByQuestionId(questionEntity.getId()));

        questionDto.setAnswerDtoList(answerDtos);
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
