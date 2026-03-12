package LibraryExercise.domain.entities;

import java.util.Objects;

public class User {
    private final String ID;
    private final String username;

    public User(String ID, String username) {
        this.ID = ID;
        this.username = username;
    }

    public String getID() {
        return ID;
    }
    public String getUsername() {
        return username;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(ID, user.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }
}
