package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.service.dto.TestDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestMapper implements MapEntityToDto<TestEntity, TestDto> {
    @Override
    public TestDto mapEntityToDto(TestEntity testEntity) {
        TestDto testDto = new TestDto();
        testDto.setId(testEntity.getId());
        testDto.setDescription(testEntity.getDescription());
        testDto.setTest_name(testEntity.getTest_name());
        testDto.setDuration(testEntity.getDuration());
        return testDto;
    }

    @Override
    public List<TestDto> mapEntitiesToDto(List<TestEntity> testEntities) {
//        List<TestDto> testDtos = new ArrayList<>();
//        for (TestEntity testEntity : testEntities) {
//            testDtos.add(mapEntityToDto(testEntity));
//        }
//        return testDtos;

        return testEntities
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());

    }
}
