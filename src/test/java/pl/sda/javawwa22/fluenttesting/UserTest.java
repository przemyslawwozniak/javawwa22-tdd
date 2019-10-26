package pl.sda.javawwa22.fluenttesting;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

public class UserTest {

    User user = new User();

    @Test
    public void calc_time_since_last_logged_in() {
        //given:
        user.lastLoggedIn = LocalDateTime.now().minusMinutes(30);   //30 min ago

        //when:
        long minutesSinceLastLoggedIn = user.timeSinceLastLoggedIn();

        //then:
        Assertions.assertThat(minutesSinceLastLoggedIn).isGreaterThanOrEqualTo(30);
    }

    @Test
    public void calc_time_since_registered() {
        // j/w
    }

    @Test
    public void creates_new_user() {
        //given & when:
        user = new User("pw92");

        //then:
        Assertions.assertThat(user)
                .hasFieldOrPropertyWithValue("username", "pw92")
                    .as("Invalid username set")
                .hasFieldOrPropertyWithValue("status", User.UserStatus.INITIALIZED)
                    .as("Newly created user not in status 'INITIALIZED'");
        Assertions.assertThat(user.registered)
                .isBetween(LocalDateTime.now().minusSeconds(1), LocalDateTime.now().plusSeconds(1))
                .as("User not registered at 'LocalDateTime.now()'");
    }

}
