package am.aca.quiz.software.service.dto;

public class TimerDto {

    private long currentTime;
    private long endTime;


    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
