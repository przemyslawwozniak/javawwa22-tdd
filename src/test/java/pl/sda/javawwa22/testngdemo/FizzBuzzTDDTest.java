package pl.sda.javawwa22.testngdemo;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class FizzBuzzTDDTest {

    @Test
    public void for_dividable_by_3_returns_fizz() {
        //given & when:
        String word = FizzBuzzTDD.play(3);

        //then:
        Assertions.assertThat(word).isEqualTo("fizz");
    }

    @Test
    public void for_dividable_by_5_returns_buzz() {
        //given & when:
        String word = FizzBuzzTDD.play(5);

        //then:
        Assertions.assertThat(word).isEqualTo("buzz");
    }

    @Test
    public void for_dividable_by_3_and_5_returns_fizzbuzz() {
        //given & when:
        String word = FizzBuzzTDD.play(15);

        //then:
        Assertions.assertThat(word).isEqualTo("fizzbuzz");
    }

    @Test
    public void for_others_returns_other() {
        //given & when:
        String word = FizzBuzzTDD.play(7);

        //then:
        Assertions.assertThat(word).isEqualTo("7");
    }

}
