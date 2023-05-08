package org.example.player.bot.strategy;

import org.example.engine.Color;

public interface DecisionStrategy {
    double calculateHeuristics(Color movingColor);
}
