package org.example.player.bot.strategy;

import org.example.engine.Board;
import org.example.engine.Color;
import org.example.move.MoveGenerator;

public class ManyAvailableMovesStrategy implements DecisionStrategy {
    @Override
    public double calculateHeuristics(Board board, Color movingColor) {
        var moveGenerator = new MoveGenerator(board);
        var movingColorAvailableMovesCount = moveGenerator.getAvailableMovesOf(movingColor).size();
        var oppositeColorAvailableMovesCount = moveGenerator.getAvailableMovesOf(movingColor.opposite()).size();
        return movingColorAvailableMovesCount - oppositeColorAvailableMovesCount;
    }
}
