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

    @Test
    public void prints_expected_text() {
        //given
        user = new User("pw92");

        //when:
        user.login();
        //username@2019-08-11T10:20:08.858
        String text = user.getLastLoggedInText();
        System.out.println(text);

        //then
        Assertions.assertThat(text)
                .matches("\\w+@\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}");
    }

    @Test
    public void status_changes_from_INITIALIZED_to_ACTIVATED() {
        //given:

        //when:
        user.setStatus(User.UserStatus.ACTIVATED);

        //then:
        Assertions.assertThat(user.status)
                .isEqualTo(User.UserStatus.ACTIVATED);
    }

    @Test
    public void status_changes_from_ACTIVATED_to_DEACTIVATED() {
        //given:

        //when:
        user.setStatus(User.UserStatus.ACTIVATED);
        user.setStatus(User.UserStatus.DEACTIVATED);

        //then:
        Assertions.assertThat(user.status)
                .isEqualTo(User.UserStatus.DEACTIVATED);
    }

    @Test
    public void status_changes_from_DEACTIVATED_to_ACTIVATED() {
        //given:

        //when:
        user.setStatus(User.UserStatus.ACTIVATED);
        user.setStatus(User.UserStatus.DEACTIVATED);
        user.setStatus(User.UserStatus.ACTIVATED);

        //then:
        Assertions.assertThat(user.status)
                .isEqualTo(User.UserStatus.ACTIVATED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void status_DOES_NOT_change_from_INITIALIZED_to_DEACTIVATED() {
        //given:

        //when:
        user.setStatus(User.UserStatus.DEACTIVATED);

        //then throws IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void status_DOES_NOT_change_from_ACTIVATED_to_INITIALIZED() {
        //given:

        //when:
        user.setStatus(User.UserStatus.ACTIVATED);
        user.setStatus(User.UserStatus.INITIALIZED);

        //then throws IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void status_DOES_NOT_change_from_DEACTIVATED_to_INITIALIZED() {
        //given:

        //when:
        user.setStatus(User.UserStatus.ACTIVATED);
        user.setStatus(User.UserStatus.DEACTIVATED);
        user.setStatus(User.UserStatus.INITIALIZED);

        //then throws IllegalArgumentException
    }

}
