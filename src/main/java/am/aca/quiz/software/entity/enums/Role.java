package am.aca.quiz.software.entity.enums;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private final String stringValue;

    public static Role getRole(String role){
        for(Role r: values()){
            if(r.stringValue.equals(role)){
                return r;
            }
        }
        return null;
    }


    Role(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}