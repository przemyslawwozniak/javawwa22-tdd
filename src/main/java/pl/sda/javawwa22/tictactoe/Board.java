package pl.sda.javawwa22.tictactoe;

import com.google.common.base.Preconditions;

public class Board {
    Sign[] signs = new Sign[9];
    Sign currentSign;

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
