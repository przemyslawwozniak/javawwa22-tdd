package pl.sda.javawwa22.imitationclasses;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LotteryMockingDemo {

    Lottery lottery = new Lottery(Arrays.asList(7, 4, 19, 24, 33, 3));

    @Ignore
    @Test
    public void user_gets_no_prize() {
        LotteryUser lotteryUser = mock(LotteryUser.class);
        when(lotteryUser.getNumbers()).thenReturn(Arrays.asList(8, 5, 20, 25, 34, 6));

        assertEquals(1.0, lottery.getPrize(lotteryUser.getNumbers()), 0.0001);
    }

    @Test
    public void user_gets_6th_prize() {
        LotteryUser lotteryUser = mock(LotteryUser.class);
        when(lotteryUser.getNumbers()).thenReturn(Arrays.asList(7, 5, 20, 25, 34, 6));

        assertEquals(100.0, lottery.getPrize(lotteryUser.getNumbers()), 0.0001);
    }

    @Ignore
    @Test
    public void user_gets_1st_prize() {
        LotteryUser lotteryUser = mock(LotteryUser.class);
        when(lotteryUser.getNumbers()).thenReturn(Arrays.asList(7, 4, 19, 24, 33, 3));

        assertEquals(Math.pow(100, 6), lottery.getPrize(lotteryUser.getNumbers()), 0.0001);
    }

}
