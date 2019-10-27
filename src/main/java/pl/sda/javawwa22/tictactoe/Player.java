package pl.sda.javawwa22.tictactoe;

public class Player {
    Board.Sign sign;

    public Player(Board.Sign sign) {
        this.sign = sign;
    }

    public void placeSign(Board board, int position) {
        board.placeSign(sign, position);
    }

    @Override
    public String toString() {
        return "Gracz " + sign.name();
    }
}
