package org.example.player.bot.strategy;

import org.example.engine.Board;
import org.example.engine.Color;

public interface DecisionStrategy {
    double calculateHeuristics(Board board, Color movingColor);
}
