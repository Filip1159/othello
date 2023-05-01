package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.move.MoveGenerator;
import org.example.strategy.DecisionStrategy;

public class DecisionTree {
    @Getter
    @Setter
    private DecisionTreeNode root;

    public DecisionTree(Board board, Color startingColor, DecisionStrategy decisionStrategy) {
        root = new DecisionTreeNode(startingColor, board, new MoveGenerator(board), null, decisionStrategy);
    }

    public void expand(int level) {
        root.expand(level);
    }

    public boolean isLeaf() {
        return root.isLeaf();
    }
}
