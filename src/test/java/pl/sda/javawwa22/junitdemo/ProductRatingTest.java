package pl.sda.javawwa22.junitdemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductRatingTest {

    //przed kazdym testem to wykona sie na nowo, bo JUnit tworzy nowa instancje KLASY TESTOWEJ
    //przed uruchomieniem kazdej metody testowej
    ProductRating pr = new ProductRating();

    @Test
    public void create() {
        assertEquals(5, pr.score);  //odczyt package-private
    }

    @Test
    public void set_score_within_boundaries() {
        pr.setScore(7);
        assertEquals(7, pr.score);
    }

    @Test
    public void set_score_lower_boundary() {
        pr.setScore(1);
        assertEquals(1, pr.score);
    }

    @Test
    public void set_score_upper_boundary() {
        pr.setScore(10);
        assertEquals(10, pr.score);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_on_setting_score_below_lower_boundary() {
        pr.setScore(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_on_setting_score_upper_boundary() {
        pr.setScore(11);
    }

}
