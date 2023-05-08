package org.example;

import lombok.Getter;
import org.example.move.MoveGenerator;
import org.example.player.AbstractPlayer;
import org.example.player.BotPlayer;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class ReversiEngine {
    @Getter
    private final Board board;
    private final MoveGenerator moveGenerator;
    private AbstractPlayer whitePlayer;
    private AbstractPlayer blackPlayer;
    public static Color movingColor;

    public ReversiEngine() {
        board = new Board();
        moveGenerator = new MoveGenerator(board);
    }

    public void addPlayer(AbstractPlayer player) {
        if (WHITE.equals(player.getColor())) {
            if (whitePlayer != null)
                throw new IllegalStateException("White player already joined!");
            else
                whitePlayer = player;
        } else if (BLACK.equals(player.getColor())) {
            if (blackPlayer != null)
                throw new IllegalStateException("Black player already joined!");
            else
                blackPlayer = player;
        } else {
            throw new IllegalArgumentException("Player is not assigned a color!");
        }
    }

    public void gameSimulation() {
        if (!arePlayersReady()) {
            throw new IllegalStateException("Players are not ready yet!");
        }
        board.print();

        while (true) {
            System.out.println("================= BLACK tour ==================");
            var applyDebug = true;
            if (applyDebug) {
                System.out.println(moveGenerator.getAvailableMovesOf(BLACK));
            }
            movingColor = BLACK;
            if (!moveGenerator.getAvailableMovesOf(BLACK).isEmpty()) {
                var blackPlayerMove = blackPlayer.makeMove();
                var blackMoveResult = moveGenerator.performMoveSimulation(blackPlayerMove, BLACK);

                board.applyMove(blackMoveResult);
                if (applyDebug) {
//                    ((BotPlayer) blackPlayer).getDecisionTree().getRoot().getBoard().print();
                    System.out.println("  - - - BLACK debug - - - ");
                    System.out.println(((BotPlayer) blackPlayer).getDecisionTree().getRoot().getMoveMade());
                    System.out.println(((BotPlayer) blackPlayer).getDecisionTree().getRoot().getMoveMadeBy());
                }
                whitePlayer.onOpponentMoveMade(blackPlayerMove);

                System.out.println("BLACK makes move: " + blackPlayerMove);
                board.print();
                System.out.println();
            } else {
                blackPlayer.makeMove();
                whitePlayer.onOpponentMoveMade(null);
                System.out.println("BLACK has no available moves");
            }

            if (isGameFinished()) break;

            // player 2
            System.out.println("================ WHITE tour ===================");
            movingColor = WHITE;
            if (!moveGenerator.getAvailableMovesOf(WHITE).isEmpty()) {
                var whitePlayerMove = whitePlayer.makeMove();
                var whiteMoveResult = moveGenerator.performMoveSimulation(whitePlayerMove, WHITE);

                if (applyDebug) {
//                    ((BotPlayer) whitePlayer).getDecisionTree().getRoot().getBoard().print();
                    System.out.println(" - - - WHITE debug - - - ");
                    System.out.println(((BotPlayer) whitePlayer).getDecisionTree().getRoot().getMoveMade());
                    System.out.println(((BotPlayer) whitePlayer).getDecisionTree().getRoot().getMoveMadeBy());
                }

                board.applyMove(whiteMoveResult);
                blackPlayer.onOpponentMoveMade(whitePlayerMove);

                System.out.println("WHITE makes move: " + whitePlayerMove);
                board.print();
                System.out.println();
            } else {
                whitePlayer.makeMove();
                blackPlayer.onOpponentMoveMade(null);
                System.out.println("WHITE has no available moves");
            }

            if (isGameFinished()) break;
        }

        board.print();
        System.out.println("BLACK: " + board.getNumberOfFieldsOf(BLACK));
        System.out.println("WHITE: " + board.getNumberOfFieldsOf(WHITE));
    }

    private boolean isGameFinished() {
        return moveGenerator.getAvailableMovesOf(BLACK).isEmpty() && moveGenerator.getAvailableMovesOf(WHITE).isEmpty();
    }

    private boolean arePlayersReady() {
        return whitePlayer != null && blackPlayer != null;
    }
}
