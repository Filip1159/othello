package org.example.decision;

import org.example.DecisionTreeNode;

public record DecisionResult(
        double heuristicsValue,
        DecisionTreeNode selectedMove
) {
}
