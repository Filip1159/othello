package org.example.player.bot.decision;

import org.example.player.bot.DecisionTree;
import org.example.player.bot.DecisionTreeNode;

import java.util.ArrayList;
import java.util.Random;

public class MinMax implements DecisionAlgorithm {
    private final Random random = new Random();

    public DecisionResult calculate(DecisionTree tree, int level) {
        return calculate(tree.getRoot(), level, true);
    }

    private DecisionResult calculate(DecisionTreeNode node, int level, boolean isMaximizing) {
        if (node.isLeaf() || level == 0 || node.getChildren().isEmpty()) {
            return new DecisionResult(node.calculateRate(), null);
        }

        var isMinimizing = !isMaximizing;

        var result = isMaximizing ? -Double.MAX_VALUE : Double.MAX_VALUE;
        var nodesWithBestHeuristics = new ArrayList<DecisionTreeNode>();
        for (var child : node.getChildren()) {
            var minMaxResult = calculate(child, level - 1, !isMaximizing);
            if (result == minMaxResult.heuristicsValue()) {
                nodesWithBestHeuristics.add(child);
            } else if (isMaximizing && result < minMaxResult.heuristicsValue()) {
                result = minMaxResult.heuristicsValue();
                nodesWithBestHeuristics.clear();
                nodesWithBestHeuristics.add(child);
            } else if (isMinimizing && result > minMaxResult.heuristicsValue()) {
                result = minMaxResult.heuristicsValue();
                nodesWithBestHeuristics.clear();
                nodesWithBestHeuristics.add(child);
            }
        }
        if (nodesWithBestHeuristics.isEmpty()) throw new IllegalStateException("internal error: cannot select move");

        return new DecisionResult(result, nodesWithBestHeuristics.get(random.nextInt(nodesWithBestHeuristics.size())));
    }
}
