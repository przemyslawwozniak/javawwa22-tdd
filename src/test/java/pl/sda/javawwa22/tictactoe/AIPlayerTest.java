package pl.sda.javawwa22.tictactoe;


import com.sun.prism.shader.AlphaOne_ImagePattern_Loader;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 1. Ruch nr 1: srodek lub jeden z brzegow
 * 2. Sprawdz, czy wrog jest o 1 znak od wygranej - jesli tak, to w danej sekwencji zablokuj go.
 * 3. Zrob ocene mozliwosci wygranej i wybierz najkorzystniejsza opcje.
 */

public class AIPlayerTest {

    Board board;
    AIPlayer playerAI;
    Player player;

    @BeforeMethod
    public void setup() {
        board = new Board(Board.Sign.X);
        playerAI = new AIPlayer(Board.Sign.X);
        player = new Player(Board.Sign.O);
    }

    @Test
    public void if_AI_player_is_first_places_sign_in_the_middle() {
        //given:
        //when:
        playerAI.placeSign(board);
        //then:
        Assertions.assertThat(board.signs[4])
                .isEqualTo(Board.Sign.X);
    }

    @Test
    public void if_AI_player_is_2nd_places_sign_in_upper_left_corner() {
        //given:
        //when:
        board = new Board(Board.Sign.O);
        player.placeSign(board, 4);
        playerAI.placeSign(board);
        //then:
        Assertions.assertThat(board.signs[0])
                .isEqualTo(Board.Sign.X);
    }

}
