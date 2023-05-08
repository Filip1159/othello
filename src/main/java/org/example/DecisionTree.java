package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.move.MoveGenerator;
import org.example.strategy.DecisionStrategy;

public class DecisionTree {
    @Getter
    @Setter
    private DecisionTreeNode root;

    public DecisionTree(ReversiEngine engine, DecisionStrategy decisionStrategy) {
        root = new DecisionTreeNode(engine.getStartingColor().opposite(), engine.getBoard(),
                new MoveGenerator(engine.getBoard()), null, decisionStrategy);
        // opposite, because I want children to be of startingColor as it is the first move made
    }

    public void expand(int level) {
        root.expand(level);
    }

    public boolean isLeaf() {
        return root.isLeaf();
    }
}
