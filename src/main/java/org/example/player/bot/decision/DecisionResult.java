package org.example.player.bot.decision;

import org.example.player.bot.DecisionTreeNode;

public record DecisionResult(
        double heuristicsValue,
        DecisionTreeNode selectedMove
) {
}
