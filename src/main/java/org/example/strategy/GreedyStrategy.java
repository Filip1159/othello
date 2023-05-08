package org.example.strategy;

import org.example.Board;
import org.example.Color;

public class GreedyStrategy implements DecisionStrategy {
    private final Board board;

    public GreedyStrategy(Board board) {
        this.board = board;
    }

    @Override
    public double calculateHeuristics(Color movingColor) {
        int numberOfColorFields = board.getNumberOfFieldsOf(movingColor);
        int numberOfOpponentFields = board.getNumberOfFieldsOf(movingColor.opposite());
        int sum = numberOfColorFields + numberOfOpponentFields;
        return ((double) (numberOfColorFields - numberOfOpponentFields)) / sum;
    }
}
