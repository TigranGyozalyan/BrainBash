package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.service.dto.HistoryDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;

import java.util.ArrayList;
import java.util.List;

public class HistoryMapper implements MapEntityToDto<HistoryEntity, HistoryDto> {

    @Override
    public  HistoryDto mapEntityToDto(HistoryEntity historyEntity){
        HistoryDto historyDto=new HistoryDto();
        historyDto.setEndTime(historyEntity.getEndTime());
        historyDto.setId(historyEntity.getId());
        historyDto.setScore(historyEntity.getScore());
        historyDto.setStartTime(historyEntity.getStartTime());
        historyDto.setStatus(historyEntity.getStatus());
        return historyDto;
    }

    @Override
    public List<HistoryDto> mapEntitiesToDto(List<HistoryEntity> historyEntityList) {
        List<HistoryDto> historyDtoList=new ArrayList<>();
        for(HistoryEntity historyEntity : historyEntityList){
            historyDtoList.add(mapEntityToDto(historyEntity));
        }
        return historyDtoList;
    }


}
