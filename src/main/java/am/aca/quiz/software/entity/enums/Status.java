package am.aca.quiz.software.entity.enums;

public enum Status {
    INPROGRESS("in progress"),
    UPCOMING("upcoming"),
    COMPLETED("completed");

    private final String stringValue;

    public static Status getStatus(String status) {
        for (Status s : values()) {
            if (s.stringValue.equals(status)) {
                return s;
            }
        }
        return null;
    }


    Status(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}