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

        signs[position] = sign;
        currentSign = currentSign.reverse();
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
