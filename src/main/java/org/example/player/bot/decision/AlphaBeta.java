package org.example.player.bot.decision;

import org.example.player.bot.DecisionTree;
import org.example.player.bot.DecisionTreeNode;

import java.util.ArrayList;
import java.util.Random;

public class AlphaBeta implements DecisionAlgorithm {
    private final Random random = new Random();

    public DecisionResult calculate(DecisionTree tree, int level) {
        return calculate(-Double.MAX_VALUE, Double.MAX_VALUE, tree.getRoot(), level, true);
    }

    private DecisionResult calculate(double alpha, double beta, DecisionTreeNode node, int level, boolean isMaximizing) {
        if (node.isLeaf() || level == 0) {
            return new DecisionResult(node.calculateRate(), null);
        }

        var nodesWithBestHeuristics = new ArrayList<DecisionTreeNode>();
        if (isMaximizing) {
            for (var child : node.getChildren()) {
                var childResult = calculate(alpha, beta, child, level - 1, false);
                if (alpha == childResult.heuristicsValue()) {
                    nodesWithBestHeuristics.add(child);
                } else if (alpha < childResult.heuristicsValue()) {
                    alpha = childResult.heuristicsValue();
                    nodesWithBestHeuristics.clear();
                    nodesWithBestHeuristics.add(child);
                }
                if (beta <= alpha)
                    break;

            }
            if (nodesWithBestHeuristics.isEmpty()) return new DecisionResult(alpha, null);
            return new DecisionResult(alpha, nodesWithBestHeuristics.get(random.nextInt(nodesWithBestHeuristics.size())));
        } else {  // isMinimizing
            for (var child : node.getChildren()) {
                var childResult = calculate(alpha, beta, child, level - 1, true);
                if (beta == childResult.heuristicsValue()) {
                    nodesWithBestHeuristics.add(child);
                } else if (beta > childResult.heuristicsValue()) {
                    beta = childResult.heuristicsValue();
                    nodesWithBestHeuristics.clear();
                    nodesWithBestHeuristics.add(child);
                }
                if (beta <= alpha)
                    break;
            }
            if (nodesWithBestHeuristics.isEmpty()) return new DecisionResult(beta, null);
            return new DecisionResult(beta, nodesWithBestHeuristics.get(random.nextInt(nodesWithBestHeuristics.size())));
        }
    }
}
