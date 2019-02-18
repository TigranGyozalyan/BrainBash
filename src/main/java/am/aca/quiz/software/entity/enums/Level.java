package am.aca.quiz.software.entity.enums;

public enum Level {
    BEGINNER("beginner"),
    INTERMEDIATE("intermediate"),
    ADVANCED("advanced");

    private final String stringValue;

    Level(String stringValue) {
        this.stringValue = stringValue;
    }

    public static Level getLevel(String level) {
        for (Level l : values()) {
            if (l.stringValue.equals(level)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}