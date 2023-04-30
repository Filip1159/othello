package org.example.strategy;

import lombok.Getter;
import org.example.Board;
import org.example.Color;
import org.example.Field;
import org.example.move.MoveGenerator;

public class GreedyStrategy {
    private final Board board;
    @Getter
    private final MoveGenerator moveGenerator;

    public GreedyStrategy(Board board) {
        this.board = board;
        moveGenerator = new MoveGenerator(board);
    }

//    @Override
//    public Field bestMove(Color movingColor) {
//        var availableMoves = moveGenerator.getAvailableMovesOf(movingColor);
//        Field bestField = null;
//        double bestFieldHeuristics = Double.MIN_VALUE;
//        for (var field : availableMoves) {
//            var heuristicsValue = calculateHeuristics(movingColor);
//            if (heuristicsValue > bestFieldHeuristics) {
//                bestFieldHeuristics = heuristicsValue;
//                bestField = field;
//            }
//        }
//        return bestField;
//    }

    public double calculateHeuristics(Color movingColor) {
        int numberOfColorFields = board.getNumberOfFieldsOf(movingColor);
        int numberOfOpponentFields = board.getNumberOfFieldsOf(movingColor.opposite());
        int sum = numberOfColorFields + numberOfOpponentFields;
        return ((double) (numberOfColorFields - numberOfOpponentFields)) / sum;
    }
}
