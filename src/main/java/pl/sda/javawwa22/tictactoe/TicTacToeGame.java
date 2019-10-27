package pl.sda.javawwa22.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

    public static void main(String[] args) {
        //1. Przywitac gracza w grze i poprosic o wybor rodzaju gry:
        //s -> gra 'single player' (z PC/AI - do zaimplementowania, na razie zwraca text 'Unimplemented'
        //m -> gra 'multi player' - z drugim graczem
        System.out.println("- - - TIC TAC TOE THE GAME - - -");
        System.out.println("Prosze wybrac rodzaj gry:\n1 - Gra dla pojedynczego gracza\n2 - Gra dla wielu graczy");

        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        while(choose != 1 && choose != 2) {
            System.out.println("Mozesz wybrac tylko sposrod opcji 1 i 2. Wybierz 1 lub 2.");
            choose = sc.nextInt();
        }

        Board board;
        Player playerA, playerB;

        if(choose == 1) {
            System.out.println("AI support coming soon");
        }
        else {
            System.out.println("Witamy w grze dla wielu graczy.");
            //2. Komputer losuje, ktory gracz zaczyna gre
            if(Math.random() > 0.5) {
                //3. Komputer tworzy obiekt Board dla gracza o wylosowanym znaku i wyswietla info, ze on zaczyna
                board = new Board(Board.Sign.X);
                playerA = new Player(Board.Sign.X);
                playerB = new Player(Board.Sign.O);
                System.out.println("Gre zaczyna gracz X");
            }
            else {
                board = new Board(Board.Sign.O);
                playerA = new Player(Board.Sign.O);
                playerB = new Player(Board.Sign.X);
                System.out.println("Gre zaczyna gracz O");
            }
            //4. Wyswietlenie pustej planszy
            board.printBoard();
            //5. Gracz rozpoczynajacy wybiera pozycje, ktora chce zajac - player.placeSign(board, pos)
            System.out.println("Wybierz pozycje.");
            choose = sc.nextInt();
            while(choose < 0 || choose > 8) {
                System.out.println("Mozesz wybrac tylko pozycje 0...8. Podaj poprawnie.");
                choose = sc.nextInt();
            }
            playerA.placeSign(board, choose);   //current sign sie zmienia

            boolean isGameWon = false;
            Player currentPlayer;
            do {
                //6. Wyswietlenie zaktualizowanej planszy
                board.printBoard();
                //7. Zmiana gracza na kolejnego i wyswietlenie info, zeby postawil znak
                currentPlayer = getCurrentPlayer(board.getCurrentSign(), playerA, playerB);
                System.out.println("Teraz ruch wykonuje gracz: " + currentPlayer);
                System.out.println("Wybierz pozycje: ");
                //8. Gracz wybiera pozycje, ktore chca zajac
                choose = sc.nextInt();
                while(choose < 0 || choose > 8) {
                    System.out.println("Mozesz wybrac tylko pozycje 0...8. Podaj poprawnie.");
                    choose = sc.nextInt();
                }
                currentPlayer.placeSign(board, choose);
                //9. Wyswietlenie zaktualizowanej planszy.
                //10. Sprawdzenie, czy gracz ktory przed chwila postawil znak, wygral
                isGameWon = board.checkWinner(board.currentSign.reverse()); //sprawdzam dla tego ktory przed chwila wykonal ruch
                //11. Zmiana gracza...
                if(isGameWon) {
                    System.out.println("MAMY ZWYCIEZCE - WYGRAL GRACZ: " + board.currentSign.reverse());
                }
                else if(board.isBoardFull()) {
                    System.out.println("REMIS!!!");
                }
            }
            while(!isGameWon && !board.isBoardFull());

        }


    }

    private static Player getCurrentPlayer(Board.Sign currentSign, Player playerA, Player playerB) {
        if(playerA.sign.equals(currentSign))
            return playerA;
        else
            return playerB;
    }

}
