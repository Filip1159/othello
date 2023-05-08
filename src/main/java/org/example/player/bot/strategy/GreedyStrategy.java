package org.example.player.bot.strategy;

import org.example.engine.Board;
import org.example.engine.Color;

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
