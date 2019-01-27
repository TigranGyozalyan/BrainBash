package am.aca.quiz.software.entity.enums;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private final String stringValue;


    Role(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
