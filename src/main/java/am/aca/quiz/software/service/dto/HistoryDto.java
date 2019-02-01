package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.enums.Status;

import java.time.LocalDateTime;

public class HistoryDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private double score;

    private TestDto testDto;
    private UserDto userDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public TestDto getTestDto() {
        return testDto;
    }

    public void setTestDto(TestDto testDto) {
        this.testDto = testDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
