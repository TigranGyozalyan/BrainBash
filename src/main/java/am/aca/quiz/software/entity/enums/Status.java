package am.aca.quiz.software.entity.enums;

public enum Status {
    INPROGRESS("in progress"),
    UPCOMING("upcoming"),
    COMPLETED("completed");

    private final String stringValue;


    Status(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}