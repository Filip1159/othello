package org.example;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class AlphaBeta {
    public static double calculate(double alpha, double beta, DecisionTreeNode node, int level) {
        if (node.isLeaf() || level == 0) {
            node.setMinmax(node.calculateRate());
            return node.calculateRate();
        }

        if (node.getPlayerColor().equals(WHITE)) {
            for (var child : node.getChildren()) {
                alpha = Math.max(alpha, calculate(alpha, beta, child, level - 1));
                if (beta <= alpha)
                    break;

            }
            node.setMinmax(alpha);
            return alpha;
        }

        else if (node.getPlayerColor().equals(BLACK)) {
            for (var child : node.getChildren()) {
                beta = Math.min(beta, calculate(alpha, beta, child, level - 1));
                if (beta <= alpha)
                    break;
            }
            node.setMinmax(beta);
            return beta;
        }
        throw new IllegalStateException("Internal error!");
    }
}
