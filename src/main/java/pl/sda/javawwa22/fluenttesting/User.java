package pl.sda.javawwa22.fluenttesting;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class User {

    String username;
    LocalDateTime lastLoggedIn;
    LocalDateTime registered;
    UserStatus status;

    //package-private
    User() {}

    public User(String username) {
        this.username = username;
        this.registered = LocalDateTime.now();
        this.status = UserStatus.INITIALIZED;
    }

    public void printLastLoggedIn() {
        System.out.println(getLastLoggedInText());
    }

    String getLastLoggedInText() {
        return username + "@" + lastLoggedIn;
    }

    public long timeSinceLastLoggedIn() {
        return calcTimeDiffInMinutes(lastLoggedIn);
    }

    public long timeSinceRegistered() {
        return calcTimeDiffInMinutes(registered);
    }

    private long calcTimeDiffInMinutes(LocalDateTime dateTime) {
        return dateTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);
    }

    public enum UserStatus {
        INITIALIZED,
        ACTIVATED,
        DEACTIVATED
    }

}
