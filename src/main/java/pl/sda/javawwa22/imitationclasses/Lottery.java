package pl.sda.javawwa22.imitationclasses;

import java.util.List;

public class Lottery {

    private List<Integer> numbers;

    public Lottery(List<Integer> numbers) {
        this.numbers = numbers;
    }

    double getPrize(List<Integer> numbers) {
        numbers.retainAll(this.numbers);
        return Math.pow(100, numbers.size());
    }
}
