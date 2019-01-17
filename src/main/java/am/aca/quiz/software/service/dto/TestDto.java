package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.TestEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TestDto {


    private Long id;

    private Date duration;
    private String test_name;
    private String description;

    public static TestDto mapEntityToDto(TestEntity testEntity) {
        TestDto testDto = new TestDto();
        testDto.setId(testEntity.getId());
        testDto.setDescription(testEntity.getDescription());
        testDto.setTest_name(testEntity.getTest_name());
        testDto.setDuration(testEntity.getDuration());
        return testDto;
    }

    public static List<TestDto> mapEntityToDtos(List<TestEntity> testEntities) {

        List<TestDto> testDtos = new ArrayList<>();

        for (TestEntity testEntity : testEntities) {
            testDtos.add(mapEntityToDto(testEntity));
        }

        return testDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
