package pl.sda.javawwa22.tictactoe;

import java.util.HashMap;
import java.util.Map;

import static pl.sda.javawwa22.tictactoe.Board.WINNING_SEQUENCES;

public class AIPlayer extends Player {
    private boolean isFirstMove = true;

    public AIPlayer(Board.Sign sign) {
        super(sign);
    }

    //AI steering
    public void placeSign(Board board) {
        if(isFirstMove) {
            doFirstMove(board);
            isFirstMove = false;
        }
        else {
            doFollowingMoves(board);
        }
    }

    void doFollowingMoves(Board board) {
        int posToBlock = isBlockingMoveNeeded(board);
        if(posToBlock >= 0) {
            board.placeSign(sign, posToBlock);
        }
        else {
            board.placeSign(sign, getBestMove(board, getBestSequence(scoreSequences(board, sign))));
        }
    }

    //returns -1 if no blocking move need
    private int isBlockingMoveNeeded(Board board) {
        String opponentsSequence = getWorstSequence(scoreSequences(board, sign.reverse()));
        return getBlockingMove(board, opponentsSequence);
    }

    private int getBlockingMove(Board board, String opponentsSequence) {
        String boardView = board.getBoardView();
        int winningPositionsTaken = 0;
        int it = 0;
        int posToBlock = -1;
        for(char c : opponentsSequence.toCharArray()) {
            if(c == 'S') {
                //Sign.X.name() = "X" -> 'X'
                if(boardView.charAt(it) == sign.reverse().name().charAt(0)) {
                    winningPositionsTaken++;
                }
                else if(boardView.charAt(it) == '-') {
                    posToBlock = it;
                }
            }
            it++;
        }
        if(winningPositionsTaken == 2 && posToBlock != -1) {
            return posToBlock;
        }
        return -1;
    }

    private int getBestMove(Board board, String bestSequence) {
        String boardView = board.getBoardView();
        int it = 0;
        int posToTake = -1;
        for(char c : bestSequence.toCharArray()) {
            if(c == 'S') {
                //Sign.X.name() = "X" -> 'X'
                if(boardView.charAt(it) == '-') {
                    posToTake = it;
                }
            }
            it++;
        }
        return posToTake;
    }

    //to-do: wspolna metoda private
    private String getWorstSequence(Map<String, Integer> sequenceToScoreMap) {
        Integer minScore = sequenceToScoreMap.values().stream().min(Integer::compareTo).get();
        for(Map.Entry entry : sequenceToScoreMap.entrySet()) {
            if(entry.getValue().equals(minScore))
                return (String)entry.getKey();
        }
        //cos tam musimy zwrocic ale przeciez zawsze zwrocimy cos z tej linii wyzej
        return "";
    }

    //to-do: wspolna metoda private
    private String getBestSequence(Map<String, Integer> sequenceToScoreMap) {
        Integer minScore = sequenceToScoreMap.values().stream().max(Integer::compareTo).get();
        for(Map.Entry entry : sequenceToScoreMap.entrySet()) {
            if(entry.getValue().equals(minScore))
                return (String)entry.getKey();
        }
        //cos tam musimy zwrocic ale przeciez zawsze zwrocimy cos z tej linii wyzej
        return "";
    }

    private Map<String, Integer> scoreSequences(Board board, Board.Sign calcForSign) {
        Map<String, Integer> sequenceToScoreMap = new HashMap<>();
        String boardView = board.getBoardView();
        for(String winningSeq : WINNING_SEQUENCES) {
            int score = 0;
            int it = 0;
            for(char c : winningSeq.toCharArray()) {
                if(c == 'S') {
                    if(boardView.charAt(it) == calcForSign.name().charAt(0)) {
                        score += 2;
                    }
                    else if(boardView.charAt(it) == calcForSign.reverse().name().charAt(0)) {
                        score -= 2;
                    }
                    else if(boardView.charAt(it) == '-') {
                        score += 1;
                    }
                }
                it++;
            }
            sequenceToScoreMap.put(winningSeq, score);
        }

        return sequenceToScoreMap;
    }

    private void doFirstMove(Board board) {
        if(board.signs[4] == null) {
            placeSign(board, 4);
        }
        else {
            //skoro nie postawil na srodku, to kazdy corner jest wolny
            placeSign(board, 0);
        }
    }

    //not needed
/*    private int findEmptyCorner(Board board) {
        int[] corners = {0, 2, 6, 8};
        for(int corner : corners) {
            if(board.signs[corner] == null)
                return corner;
        }
    }*/
}
