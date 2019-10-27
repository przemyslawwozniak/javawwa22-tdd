package pl.sda.javawwa22.testngdemo;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Random;

public class FizzBuzzTDDTest {

    Random rand = new Random();

    @Test(dataProvider = "dividables")
    public void for_dividable_by_X_returns_Y(int no, String expectedWord) {
        //given & when:
        String word = FizzBuzzTDD.play(no);

        //then:
        Assertions.assertThat(word).isEqualTo(expectedWord);
    }

    @Ignore
    @Test
    public void for_dividable_by_5_returns_buzz() {
        //given & when:
        String word = FizzBuzzTDD.play(5);

        //then:
        Assertions.assertThat(word).isEqualTo("buzz");
    }

    @Ignore
    @Test
    public void for_dividable_by_3_and_5_returns_fizzbuzz() {
        //given & when:
        String word = FizzBuzzTDD.play(15);

        //then:
        Assertions.assertThat(word).isEqualTo("fizzbuzz");
    }

    @Test(invocationCount = 1000)
    public void for_others_returns_other() {
        //given & when:
        int no = generateNoNotDivBy3And5();
        String word = FizzBuzzTDD.play(no);

        //then:
        Assertions.assertThat(word).isEqualTo(Integer.toString(no));
    }

    @DataProvider(name = "dividables")
    public Object[][] createData1() {
        return new Object[][] {
                {3, "fizz"},
                {6, "fizz"},
                {9, "fizz"},
                {12, "fizz"},
                {18, "fizz"},
                {21, "fizz"},
                {24, "fizz"},
                {27, "fizz"},
                {303, "fizz"},
                {909, "fizz"},
                {5, "buzz"},
                {15, "fizzbuzz"},
                {50, "buzz"},
                {25, "buzz"},
                {255, "fizzbuzz"},
                {65, "buzz"},
                {505, "buzz"},
                {10, "buzz"},
                {15, "fizzbuzz"},
                {150, "fizzbuzz"},
                {300, "fizzbuzz"},
                {900, "fizzbuzz"},
                {30, "fizzbuzz"},
                {45, "fizzbuzz"},
                {90, "fizzbuzz"},
        };
    }

    private Object[][] generateTestData() {
        int randNo = rand.nextInt();
        String word = FizzBuzzTDD.play(randNo);

        return new Object[][]{{randNo, word}};
    }

    private int generateNoNotDivBy3And5() {
        int randNo;

        do {
            randNo = rand.nextInt(100000);
        }
        while(randNo %3 == 0 || randNo % 5 == 0);

        return randNo;
    }

}
