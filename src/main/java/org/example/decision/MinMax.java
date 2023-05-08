package org.example.decision;

import org.example.DecisionTree;
import org.example.DecisionTreeNode;
import org.example.ReversiEngine;

public class MinMax implements DecisionAlgorithm {

    public DecisionResult calculate(DecisionTree tree, int level) {
        return calculate(tree.getRoot(), level);
    }

    private DecisionResult calculate(DecisionTreeNode node, int level) {
        if (node.isLeaf() || level == 0 || node.getChildren().isEmpty()) {
            return new DecisionResult(node.calculateRate(), null);
        }

        var isMaximizing = node.getMoveMadeBy().opposite().equals(ReversiEngine.movingColor);
        var isMinimizing = !isMaximizing;

        var result = isMaximizing ? -Double.MAX_VALUE : Double.MAX_VALUE;
        DecisionTreeNode resultNode = null;
        for (var child : node.getChildren()) {
            var minMaxResult = calculate(child, level - 1);
            if (isMaximizing && result <= minMaxResult.heuristicsValue()) {
                result = minMaxResult.heuristicsValue();
                resultNode = child;
            } else if (isMinimizing && result >= minMaxResult.heuristicsValue()) {
                result = minMaxResult.heuristicsValue();
                resultNode = child;
            }
        }
        if (resultNode == null) throw new IllegalStateException("result node cannot be null, movingColor = " + ReversiEngine.movingColor + ", result = " + result);

        node.setMinmax(result);
        return new DecisionResult(result, resultNode);
    }
}
