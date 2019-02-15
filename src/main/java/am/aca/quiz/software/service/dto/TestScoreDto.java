package am.aca.quiz.software.service.dto;

public class TestScoreDto {

    private Double userScore;
    private Double testScore;

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public Double getTestScore() {
        return testScore;
    }

    public void setTestScore(Double testScore) {
        this.testScore = testScore;
    }
}
