package org.example.strategy;

import org.example.Color;
import org.example.move.MoveResult;

public interface DecisionStrategy {
    MoveResult bestMove(Color movingColor);
    double calculateHeuristics(Color movingColor);
}
