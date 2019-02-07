package am.aca.quiz.software.service.dto;

import java.util.List;

public class TestUsersDto {

    private Long testId;
    private List<Long> usersId;


    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public List<Long> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Long> usersId) {
        this.usersId = usersId;
    }
}
