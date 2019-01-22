package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.HistoryEntity;

import java.time.LocalDateTime;

public class HistoryDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private HistoryEntity.Status status;
    private double score;


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

    public HistoryEntity.Status getStatus() {
        return status;
    }

    public void setStatus(HistoryEntity.Status status) {
        this.status = status;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
