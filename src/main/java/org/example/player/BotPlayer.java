package org.example.player;

import org.example.*;
import org.example.strategy.DecisionStrategy;

import static org.example.Color.WHITE;

public class BotPlayer extends AbstractPlayer {
    private final DecisionTree decisionTree;
    private static final int DECISION_TREE_EXPANSION_DEPTH = 5;

    public BotPlayer(Color color, DecisionStrategy strategy, Board board) {
        super(color);
        decisionTree = new DecisionTree(board, WHITE, strategy);
        decisionTree.expand(DECISION_TREE_EXPANSION_DEPTH);
    }

    @Override
    public Field makeMove() {
        if (decisionTree.isLeaf())
            throw new IllegalStateException("No possible moves exist");

        decisionTree.expand(DECISION_TREE_EXPANSION_DEPTH);
        var maxValue = MinMax.calculate(decisionTree, DECISION_TREE_EXPANSION_DEPTH);

        for (var child : decisionTree.getRoot().getChildren()) {
            if (child.getMinmax() == maxValue) {
                decisionTree.setRoot(child);
            }
        }
        return decisionTree.getRoot().getMoveMade();
    }

    @Override
    public void onOpponentMoveMade(Field field) {
        var childSelectedByOpponent = decisionTree.getRoot().getChildByMoveMade(field);
        decisionTree.setRoot(childSelectedByOpponent);
    }
}
