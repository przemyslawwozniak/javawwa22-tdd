package pl.sda.javawwa22.junitdemo;

public class ProductRating {

    int score;

    public ProductRating() {
        this.score = 5;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if(score < 1 || score > 10)
            throw new IllegalArgumentException();

        this.score = score;
    }

}
