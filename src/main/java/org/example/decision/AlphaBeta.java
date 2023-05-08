package org.example.decision;

import org.example.DecisionTree;
import org.example.DecisionTreeNode;

public class AlphaBeta implements DecisionAlgorithm {

    public DecisionResult calculate(DecisionTree tree, int level) {
        return calculate(Double.MIN_VALUE, Double.MAX_VALUE, tree.getRoot(), level);
    }

    private DecisionResult calculate(double alpha, double beta, DecisionTreeNode node, int level) {
//        if (node.isLeaf() || level == 0) {
//            node.setMinmax(node.calculateRate());
//            return new DecisionResult(node.calculateRate(), null);
//        }
//
//        if (node.getMoveMadeBy().equals(WHITE)) {
//            for (var child : node.getChildren()) {
//                var childResult = calculate(alpha, beta, child, level - 1);
//                if (alpha < childResult.heuristicsValue()) {
//                    alpha = childResult.heuristicsValue();
//                }
//                if (beta <= alpha)
//                    break;
//
//            }
//            node.setMinmax(alpha);
//            return alpha;
//        }
//
//        else if (node.getMoveMadeBy().equals(BLACK)) {
//            for (var child : node.getChildren()) {
//                beta = Math.min(beta, calculate(alpha, beta, child, level - 1));
//                if (beta <= alpha)
//                    break;
//            }
//            node.setMinmax(beta);
//            return beta;
//        }
        throw new IllegalStateException("Internal error!");
    }
}
