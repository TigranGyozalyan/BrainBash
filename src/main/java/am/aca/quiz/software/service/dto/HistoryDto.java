package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.HistoryEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class HistoryDto {
    private Long id;
    private Date startTime;
    private Date endTime;
    private String status;
    private double score;

    public static HistoryDto mapEntityToDto(HistoryEntity historyEntity){
        HistoryDto historyDto=new HistoryDto();
        historyDto.setEndTime(historyEntity.getEndTime());
        historyDto.setId(historyEntity.getId());
        historyDto.setScore(historyEntity.getScore());
        historyDto.setStartTime(historyEntity.getStartTime());
        historyDto.setStatus(historyEntity.getStatus());
        return historyDto;
    }

    public static List<HistoryDto> mapEntitesToDto(List<HistoryEntity> historyEntityList){
        List<HistoryDto> historyDtoList=new ArrayList<>();
        for(HistoryEntity historyEntity : historyEntityList){
            historyDtoList.add(mapEntityToDto(historyEntity));
        }
        return historyDtoList;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
