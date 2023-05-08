package org.example.player.bot.strategy;

import org.example.engine.Board;
import org.example.engine.Color;

public class CornersStrategy implements DecisionStrategy {
    private static final int[][] fieldWeights = new int[][]{
            { 4, -3,  2,  2,  2,  2, -3,  4},
            {-3, -4, -1, -1, -1, -1, -4, -3},
            { 2, -1,  1,  0,  0,  1, -1,  2},
            { 2, -1,  0,  1,  1,  0, -1,  2},
            { 2, -1,  0,  1,  1,  0, -1,  2},
            { 2, -1,  1,  0,  0,  1, -1,  2},
            {-3, -4, -1, -1, -1, -1, -4, -3},
            { 4, -3,  2,  2,  2,  2, -3,  4}
    };
    private final Board board;

    public CornersStrategy(Board board) {
        this.board = board;
    }

    @Override
    public double calculateHeuristics(Color movingColor) {
        var colorResult = 0;
        var opponentResult = 0;
        for (int row = 0; row < 8; row++)
            for (int column = 0; column < 8; column++)
                if (movingColor.equals(board.colorAt(row, column)))
                    colorResult += fieldWeights[row][column];
                else if (movingColor.opposite().equals(board.colorAt(row, column)))
                    opponentResult += fieldWeights[row][column];

        return colorResult - opponentResult;
    }
}
