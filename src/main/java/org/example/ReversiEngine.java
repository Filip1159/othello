package org.example;

import lombok.Getter;
import org.example.move.MoveGenerator;
import org.example.player.AbstractPlayer;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class ReversiEngine {
    @Getter
    private final Board board;
    private final MoveGenerator moveGenerator;
    private AbstractPlayer whitePlayer;
    private AbstractPlayer blackPlayer;
    public static Color movingColor;
    @Getter
    private final Color startingColor;

    public ReversiEngine(Color startingColor) {
        this.startingColor = startingColor;
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
        if (!isGameReady()) {
            throw new IllegalStateException("Players are not ready yet!");
        }
        board.print();

        movingColor = startingColor;
        while (true) {
            System.out.println("================= " + movingColor + " tour ==================");
            var movingPlayer = getMovingPlayer();
            var opponentOfMovingPlayer = getOpponentOfMovingPlayer();
            if (!moveGenerator.getAvailableMovesOf(movingColor).isEmpty()) {
                var move = movingPlayer.makeMove();
                var moveResult = moveGenerator.performMoveSimulation(move, movingColor);

                board.applyMove(moveResult);
                opponentOfMovingPlayer.onOpponentMoveMade(move);

                System.out.println(movingColor + " makes move: " + move);
                board.print();
                System.out.println();
            } else {
                movingPlayer.makeMove();
                opponentOfMovingPlayer.onOpponentMoveMade(null);
                System.out.println(movingColor + " has no available moves");
            }
            movingColor = movingColor.opposite();

            if (isGameFinished()) break;

            // player 2
//            System.out.println("================ WHITE tour ===================");
//            movingColor = WHITE;
//            if (!moveGenerator.getAvailableMovesOf(WHITE).isEmpty()) {
//                var whitePlayerMove = whitePlayer.makeMove();
//                var whiteMoveResult = moveGenerator.performMoveSimulation(whitePlayerMove, WHITE);
//
//                board.applyMove(whiteMoveResult);
//                blackPlayer.onOpponentMoveMade(whitePlayerMove);
//
//                System.out.println("WHITE makes move: " + whitePlayerMove);
//                board.print();
//                System.out.println();
//            } else {
//                whitePlayer.makeMove();
//                blackPlayer.onOpponentMoveMade(null);
//                System.out.println("WHITE has no available moves");
//            }
//
//            if (isGameFinished()) break;
        }

        board.print();
        System.out.println("BLACK: " + board.getNumberOfFieldsOf(BLACK));
        System.out.println("WHITE: " + board.getNumberOfFieldsOf(WHITE));
    }

    private AbstractPlayer getMovingPlayer() {
        return switch (movingColor) {
            case WHITE -> whitePlayer;
            case BLACK -> blackPlayer;
        };
    }

    private AbstractPlayer getOpponentOfMovingPlayer() {
        return switch (movingColor) {
            case WHITE -> blackPlayer;
            case BLACK -> whitePlayer;
        };
    }

    private boolean isGameFinished() {
        return moveGenerator.getAvailableMovesOf(BLACK).isEmpty() && moveGenerator.getAvailableMovesOf(WHITE).isEmpty();
    }

    private boolean isGameReady() {
        return startingColor != null && whitePlayer != null && blackPlayer != null;
    }
}
