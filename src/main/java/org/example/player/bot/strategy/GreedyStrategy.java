package org.example.player.bot.strategy;

import lombok.RequiredArgsConstructor;
import org.example.engine.Board;
import org.example.engine.Color;

@RequiredArgsConstructor
public class GreedyStrategy implements DecisionStrategy {

    @Override
    public double calculateHeuristics(Board board, Color movingColor) {
        int numberOfColorFields = board.getNumberOfFieldsOf(movingColor);
        int numberOfOpponentFields = board.getNumberOfFieldsOf(movingColor.opposite());
        int sum = numberOfColorFields + numberOfOpponentFields;
        return ((double) (numberOfColorFields - numberOfOpponentFields)) / sum;
    }
}
