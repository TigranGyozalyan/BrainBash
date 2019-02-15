package am.aca.quiz.software.service.dto;

import java.util.List;

public class SubmitQuestionDto {

    private Long questionId;

    private List<Long> chosenAnswerList;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<Long> getChosenAnswerList() {
        return chosenAnswerList;
    }

    public void setChosenAnswerList(List<Long> chosenAnswerList) {
        this.chosenAnswerList = chosenAnswerList;
    }
}
