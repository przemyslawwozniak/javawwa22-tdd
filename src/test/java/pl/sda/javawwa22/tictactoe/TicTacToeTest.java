package pl.sda.javawwa22.tictactoe;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

    @Test
    public void players_move_in_legal_sequence() {
        //given:

        //when:
        playerX.placeSign(board, 4);
        playerO.placeSign(board, 3);
        playerX.placeSign(board, 2);
        playerO.placeSign(board, 1);

        //then:
        Assertions.assertThat(board.signs)
                .contains(null, Board.Sign.O, Board.Sign.X,
                        Board.Sign.O, Board.Sign.X, null,
                        null, null, null);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void players_move_in_illegal_sequence() {
        //given:

        //when:
        playerX.placeSign(board, 4);
        playerX.placeSign(board, 3);

        //then:
        //throws
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void player_cannot_place_sign_on_already_taken_square() {
        //given:

        //when:
        playerX.placeSign(board, 4);
        playerO.placeSign(board, 3);
        playerX.placeSign(board, 4);

        //then:
        //throws
    }

    @Test(dataProvider = "allowedPositions")
    public void player_can_place_sign_on_positions_0_to_8(int position) {
        playerX.placeSign(board, position);
    }

    @Test(dataProvider = "notAllowedPositions", expectedExceptions = {IllegalArgumentException.class})
    public void player_cannot_place_sign_outside_of_the_board(int position) {
        playerX.placeSign(board, position);
    }

    /*
    0|X|2\n
    O|X|X\n
    6|O|8\n
     */
    @Test
    public void prints_board() {
        playerX.placeSign(board, 4);
        playerO.placeSign(board, 3);
        playerX.placeSign(board, 1);
        playerO.placeSign(board, 7);
        playerX.placeSign(board, 5);

        board.printBoard();

        //then:
        Assertions.assertThat(board.getPrintableBoard())
                .isEqualTo("0|X|2\nO|X|X\n6|O|8\n");
    }

    @DataProvider(name = "allowedPositions")
    private Object[][] getAllowedPositions() {
        return new Object[][] {
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}
        };
    }

    @DataProvider(name = "notAllowedPositions")
    private Object[][] getNotAllowedPositions() {
        return new Object[][] {
                {-1}, {-5}, {9}, {99}, {13}, {11}, {-100}
        };
    }

}
