package org.example;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class MinMax {
    public static double calculate(DecisionTreeNode node, int level) {
        if (node.isLeaf() || level == 0) {
            node.calculateRate();
        }

        var minMaxValue = Double.MAX_VALUE;
        for (var child : node.getChildren()) {
            var minMaxResult = calculate(child, level - 1);
            if (node.getPlayerColor().equals(WHITE) && minMaxValue < minMaxResult)
                minMaxValue = minMaxResult;
            else if (node.getPlayerColor().equals(BLACK) && minMaxValue > minMaxResult)
                minMaxValue = minMaxResult;
        }
        node.setMinmax(minMaxValue);
        return minMaxValue;
    }

    public static double calculate(DecisionTree tree, int level) {
        return calculate(tree.getRoot(), level);
    }
}
