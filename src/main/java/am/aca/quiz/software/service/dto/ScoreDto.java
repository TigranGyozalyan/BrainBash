package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.ScoreEntity;

import java.util.ArrayList;
import java.util.List;

public class ScoreDto {
    private Long id;
    private double value;
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
