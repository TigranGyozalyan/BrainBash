package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.service.dto.HistoryDto;
import am.aca.quiz.software.service.implementations.TestServiceImp;
import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryMapper implements MapEntityToDto<am.aca.quiz.software.entity.HistoryEntity, HistoryDto> {
    private UserServiceImp userServiceImp;
    private UserMapper userMapper;
    private TestServiceImp testServiceImp;
    private TestMapper testMapper;

    public HistoryMapper(UserMapper userMapper, TestMapper testMapper, TestServiceImp testServiceImp) {
        this.userMapper = userMapper;
        this.testMapper = testMapper;
        this.testServiceImp = testServiceImp;
    }

    @Override
    public HistoryDto mapEntityToDto(am.aca.quiz.software.entity.HistoryEntity historyEntity) {
        HistoryDto historyDto = new HistoryDto();

        historyDto.setEndTime(historyEntity.getEndTime());
        historyDto.setId(historyEntity.getId());
        historyDto.setScore(historyEntity.getScore());
        historyDto.setStartTime(historyEntity.getStartTime());
        historyDto.setStatus(historyEntity.getStatus());

        historyDto.setUserId(historyEntity.getUserEntity().getId());
        historyDto.setTestId(historyEntity.getTestEntity().getId());
        historyDto.setTestDto(testMapper.mapEntityToDto(historyEntity.getTestEntity()));
        historyDto.setUserDto(userMapper.mapEntityToDto(historyEntity.getUserEntity()));

        return historyDto;
    }

    @Override
    public List<HistoryDto> mapEntitiesToDto(List<am.aca.quiz.software.entity.HistoryEntity> historyEntityList) {

        return historyEntityList
            .stream()
            .map(this::mapEntityToDto)
            .collect(Collectors.toList());

    }


}
