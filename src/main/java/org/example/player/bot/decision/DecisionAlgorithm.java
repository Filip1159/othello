package org.example.player.bot.decision;

import org.example.player.bot.DecisionTree;

public interface DecisionAlgorithm {
    DecisionResult calculate(DecisionTree tree, int level);
}
