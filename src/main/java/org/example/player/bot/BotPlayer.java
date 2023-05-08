package org.example.player.bot;

import lombok.Getter;
import org.example.engine.Color;
import org.example.engine.Field;
import org.example.engine.ReversiEngine;
import org.example.player.bot.decision.DecisionAlgorithm;
import org.example.player.AbstractPlayer;
import org.example.player.bot.strategy.DecisionStrategy;

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
        decisionTree.setRoot(childSelectedByOpponent);
    }
}
