package pl.sda.javawwa22.fluenttesting;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class User {

    String username;
    LocalDateTime lastLoggedIn;
    LocalDateTime registered;
    UserStatus status;

    //package-private
    User() {
        this.registered = LocalDateTime.now();
        this.status = UserStatus.INITIALIZED;
    }

    public User(String username) {
        this.username = username;
        this.registered = LocalDateTime.now();
        this.status = UserStatus.INITIALIZED;
    }

    public void login() {
        this.lastLoggedIn = LocalDateTime.now();
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

    public void setStatus(UserStatus status) {
        this.status = this.status.next(status);
    }

    public enum UserStatus {
        INITIALIZED {
            @Override
            public UserStatus next(UserStatus status) {
                if(status.equals(DEACTIVATED))
                    throw new IllegalArgumentException();

                return ACTIVATED;
            }
        },
        ACTIVATED {
            @Override
            public UserStatus next(UserStatus status) {
                if(status.equals(INITIALIZED))
                    throw new IllegalArgumentException();

                return DEACTIVATED;
            }
        },
        DEACTIVATED {
            @Override
            public UserStatus next(UserStatus status) {
                if(status.equals(INITIALIZED))
                    throw new IllegalArgumentException();

                return ACTIVATED;
            }
        };

        public abstract UserStatus next(UserStatus status);
    }

}
