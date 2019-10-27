package pl.sda.javawwa22.tictactoe;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TicTacToeTest {

    Board board;
    Player playerX = new Player(Board.Sign.X);
    Player playerO = new Player(Board.Sign.O);

    @BeforeMethod
    public void reinitializeBoard() {
        board = new Board(Board.Sign.X);
    }

    @Test
    public void create_empty_board_3_x_3() {
        //given:

        //when:

        //then:
        Assertions.assertThat(board.signs)
                .hasSize(9)
                    .as("Does not fit expected size - is: " + board.signs.length)
                .containsOnlyNulls()
                    .as("Does not contain ONLY nulls");
    }

    @Test
    public void player_X_starts_game() {
        //given:

        //when:
        playerX.placeSign(board, 4);
        //then:
        Assertions.assertThat(board.signs[4])
                .isEqualTo(Board.Sign.X);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void player_X_starts_game_player_O_tries_to_start_and_throws() {
        //given:

        //when:
        playerO.placeSign(board, 4);    //throws here...
        //then:
        //...throws
    }

}
