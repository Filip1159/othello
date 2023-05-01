package org.example.strategy;

import lombok.Getter;
import org.example.Board;
import org.example.Color;
import org.example.move.MoveGenerator;
import org.example.move.MoveResult;

public class GreedyStrategy implements DecisionStrategy {
    private final Board board;
    @Getter
    private final MoveGenerator moveGenerator;

    public GreedyStrategy(Board board) {
        this.board = board;
        moveGenerator = new MoveGenerator(board);
    }

    @Override
    public MoveResult bestMove(Color movingColor) {
        var availableMoves = moveGenerator.getAvailableMovesOf(movingColor);
        MoveResult bestMove = null;
        double bestMoveHeuristics = Double.MIN_VALUE;
        for (var move : availableMoves) {
            var heuristicsValue = calculateHeuristics(movingColor);
            if (heuristicsValue > bestMoveHeuristics) {
                bestMoveHeuristics = heuristicsValue;
                bestMove = move;
            }
        }
        return bestMove;
    }

    @Override
    public double calculateHeuristics(Color movingColor) {
        int numberOfColorFields = board.getNumberOfFieldsOf(movingColor);
        int numberOfOpponentFields = board.getNumberOfFieldsOf(movingColor.opposite());
        int sum = numberOfColorFields + numberOfOpponentFields;
        return ((double) (numberOfColorFields - numberOfOpponentFields)) / sum;
    }
}
