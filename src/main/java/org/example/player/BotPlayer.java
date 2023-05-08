package org.example.player;

import lombok.Getter;
import org.example.Color;
import org.example.DecisionTree;
import org.example.Field;
import org.example.ReversiEngine;
import org.example.decision.DecisionAlgorithm;
import org.example.strategy.DecisionStrategy;

public class BotPlayer extends AbstractPlayer {
    @Getter
    private final DecisionTree decisionTree;
    private final DecisionAlgorithm decisionAlgorithm;
    private final int expansionDepth;

    public BotPlayer(Color color, DecisionStrategy strategy, DecisionAlgorithm decisionAlgorithm,
                     ReversiEngine engine, int expansionDepth) {
        super(color);
        this.expansionDepth = expansionDepth;
        this.decisionAlgorithm = decisionAlgorithm;
        decisionTree = new DecisionTree(engine, strategy);
        decisionTree.expand(expansionDepth);
    }

    @Override
    public Field makeMove() {
        if (decisionTree.isLeaf())
            throw new IllegalStateException("No possible moves exist");

        decisionTree.expand(expansionDepth);
        var decisionResult = decisionAlgorithm.calculate(decisionTree, expansionDepth);
        decisionTree.setRoot(decisionResult.selectedMove());
        return decisionTree.getRoot().getMoveMade();
    }

    @Override
    public void onOpponentMoveMade(Field field) {
        var childSelectedByOpponent = decisionTree.getRoot().getChildByMoveMade(field);
        System.out.println("Hearings from " + color);
        System.out.println("This is the move that opponent made (at least my decision tree thinks so)");
        childSelectedByOpponent.getBoard().print();
        System.out.println(childSelectedByOpponent.getMoveMadeBy());
        System.out.println(childSelectedByOpponent.getMoveMade());
        System.out.println("Now, my children are:");
        childSelectedByOpponent.getChildren().forEach(c -> {
            c.getBoard().print();
            System.out.println(c.getMoveMade());
            System.out.println(c.getMoveMadeBy());
        });
        System.out.println("End of my children\n");
        decisionTree.setRoot(childSelectedByOpponent);
        System.out.println("Trying to expand my new root");
        decisionTree.expand(4);
        System.out.println(decisionTree.getRoot().getChildren());
    }
}
