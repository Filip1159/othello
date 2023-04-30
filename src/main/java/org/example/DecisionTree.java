package org.example;

import org.example.move.MoveGenerator;

public class DecisionTree {
    private final DecisionTreeNode root;

    public DecisionTree(Board board, Color playerColor) {
        root = new DecisionTreeNode(playerColor, board, new MoveGenerator(board));
    }

    public void expand(int level) {
        root.expand(level);
    }
}
