package pl.sda.javawwa22.tictactoe;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    Sign[] signs = new Sign[9];
    Sign currentSign;

    static List<String> WINNING_SEQUENCES = Arrays.asList(
        "S--S--S--",
        "-S--S--S-",
        "--S--S--S",
        "SSS------",
        "---SSS---",
        "------SSS",
        "S---S---S",
        "--S-S-S--"
    );

    public Board(Sign startingSign) {
        currentSign = startingSign;
    }

    public void placeSign(Sign sign, int position) {
        Preconditions.checkNotNull(sign);
        Preconditions.checkArgument(sign.equals(currentSign),
                "Now is the turn of the player %s", currentSign.name());
        Preconditions.checkArgument(position >= 0 && position <= 8,
                "Only positions 0-8 can be taken, not %d", position);
        Preconditions.checkArgument(signs[position] == null,
                "Position %d already taken", position);

        signs[position] = sign;
        currentSign = currentSign.reverse();
    }

    public void printBoard() {
        System.out.println(getPrintableBoard());
    }

    String getPrintableBoard() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < signs.length; i++) {
            if(signs[i] == null) {
                stringBuilder.append(i);
            }
            else {
                stringBuilder.append(signs[i].name());
            }
            if(i == 2 || i == 5 || i == 8) {
                stringBuilder.append("\n");
            }
            else {
                stringBuilder.append("|");
            }
        }

        return stringBuilder.toString();
    }

    public boolean checkWinner(Sign sign) {
        String boardView = getBoardView();
        for(String winningSeq : WINNING_SEQUENCES) {
            int winningPositionsTaken = 0;
            int it = 0;
            for(char c : winningSeq.toCharArray()) {
                if(c == 'S') {
                    //Sign.X.name() = "X" -> 'X'
                    if(boardView.charAt(it) == sign.name().charAt(0)) {
                        winningPositionsTaken++;
                    }
                }
                it++;
            }
            if(winningPositionsTaken == 3) {
                return true;
            }
        }
        return false;
    }

    //metoda typu query - 'odpytuje o dane', nie zmienia stanu wewnetrznego obiektu
    String getBoardView() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Sign s : signs) {
            if(s == null) {
                stringBuilder.append("-");
            }
            else {
                stringBuilder.append(s.name());
            }
        }

        return stringBuilder.toString();
    }

    public enum Sign {
        X {
            @Override
            public Sign reverse() {
                return O;
            }
        },
        O {
            @Override
            public Sign reverse() {
                return X;
            }
        };

        public abstract Sign reverse();
    }
}
