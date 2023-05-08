package org.example.decision;

import org.example.DecisionTree;
import org.example.DecisionTreeNode;
import org.example.ReversiEngine;

public class AlphaBeta implements DecisionAlgorithm {

    public DecisionResult calculate(DecisionTree tree, int level) {
        return calculate(-Double.MAX_VALUE, Double.MAX_VALUE, tree.getRoot(), level);
    }

    private DecisionResult calculate(double alpha, double beta, DecisionTreeNode node, int level) {
        if (node.isLeaf() || level == 0) {
            node.setMinmax(node.calculateRate());
            return new DecisionResult(node.calculateRate(), null);
        }

        var isMaximizing = node.getMoveMadeBy().opposite().equals(ReversiEngine.movingColor);

        DecisionTreeNode resultNode = null;
        if (isMaximizing) {
            for (var child : node.getChildren()) {
                var childResult = calculate(alpha, beta, child, level - 1);
                if (alpha <= childResult.heuristicsValue()) {
                    alpha = childResult.heuristicsValue();
                    resultNode = child;
                }
                if (beta <= alpha)
                    break;

            }
            node.setMinmax(alpha);
            return new DecisionResult(alpha, resultNode);
        }

        else {  // isMinimizing
            for (var child : node.getChildren()) {
                var childResult = calculate(alpha, beta, child, level - 1);
                if (beta >= childResult.heuristicsValue()) {
                    beta = childResult.heuristicsValue();
                    resultNode = child;
                }
                if (beta <= alpha)
                    break;
            }
            node.setMinmax(beta);
            return new DecisionResult(beta, resultNode);
        }
    }
}
