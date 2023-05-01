package org.example;

import lombok.Getter;
import org.example.move.MoveGenerator;
import org.example.player.BotPlayer;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class ReversiEngine {
    @Getter
    private final Board board;
    private final MoveGenerator moveGenerator;
    private BotPlayer whitePlayer;
    private BotPlayer blackPlayer;

    public ReversiEngine() {
        board = new Board();
        moveGenerator = new MoveGenerator(board);
    }

    public void addPlayer(BotPlayer player) {
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
            if (!moveGenerator.getAvailableMovesOf(WHITE).isEmpty()) {
                var whitePlayerMove = whitePlayer.makeMove();
                var whiteMoveResult = moveGenerator.performMoveSimulation(whitePlayerMove, WHITE);
                board.applyMove(whiteMoveResult);
                blackPlayer.onOpponentMoveMade(whitePlayerMove);

                System.out.println("WHITE move");
                board.print();
                System.out.println();
            }

            if (isGameFinished()) break;

            // player 2
            if (!moveGenerator.getAvailableMovesOf(BLACK).isEmpty()) {
                var blackPlayerMove = blackPlayer.makeMove();
                var blackMoveResult = moveGenerator.performMoveSimulation(blackPlayerMove, BLACK);
                board.applyMove(blackMoveResult);
                whitePlayer.onOpponentMoveMade(blackPlayerMove);

                System.out.println("BLACK move");
                board.print();
                System.out.println();
            }

            if (isGameFinished()) break;
        }

        board.print();
        System.out.println(board.getNumberOfFieldsOf(WHITE));
        System.out.println(board.getNumberOfFieldsOf(BLACK));
    }

    private boolean isGameFinished() {
        return moveGenerator.getAvailableMovesOf(BLACK).isEmpty() && moveGenerator.getAvailableMovesOf(WHITE).isEmpty();
    }

    private boolean arePlayersReady() {
        return whitePlayer != null && blackPlayer != null;
    }
}
