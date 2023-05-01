package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.move.MoveGenerator;

public class DecisionTree {
    @Getter
    @Setter
    private DecisionTreeNode root;

    public DecisionTree(Board board, Color playerColor) {
        root = new DecisionTreeNode(playerColor, board, new MoveGenerator(board));
    }

    public void expand(int level) {
        root.expand(level);
    }

    public boolean isLeaf() {
        return root.isLeaf();
    }
}
