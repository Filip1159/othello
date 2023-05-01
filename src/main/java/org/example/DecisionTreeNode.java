package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.move.MoveGenerator;
import org.example.strategy.GreedyStrategy;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DecisionTreeNode {
    @Getter
    private final Color playerColor;
    @Getter
    private final Board board;
    private final MoveGenerator moveGenerator;
    @Getter
    private final List<DecisionTreeNode> children = new ArrayList<>();
    @Getter
    private boolean isLeaf = false;
    private Double rate = null;

    @Getter
    @Setter
    private double minmax = 0;
    private boolean expanded = false;

    public void expand(int level) {
        if (level <= 0) return;

        if (!expanded) {
            var availableMoves = moveGenerator.getAvailableMovesOf(playerColor);
            expanded = true;

            if (!availableMoves.isEmpty()) {
                for (var move : availableMoves) {
                    var boardCopy = board.copy();
                    boardCopy.applyMove(move);
                    var child = new DecisionTreeNode(playerColor.opposite(), boardCopy, new MoveGenerator(boardCopy));
                    children.add(child);
                    child.expand(level - 1);
                }
            } else {
                var opponentMoves = moveGenerator.getAvailableMovesOf(playerColor.opposite());
                if (opponentMoves.isEmpty()) {
                    isLeaf = true;
                } else {
                    var boardCopy = board.copy();
                    var node = new DecisionTreeNode(playerColor.opposite(), boardCopy, new MoveGenerator(boardCopy));
                    children.add(node);
                }
            }
        } else {
            for (var child : children)
                child.expand(level - 1);
        }
    }

    public double calculateRate() {
        if (rate == null) {
            if (isLeaf) {
                var playerDisks = board.getNumberOfFieldsOf(playerColor);
                var opponentDisks = board.getNumberOfFieldsOf(playerColor.opposite());

                if (playerDisks > opponentDisks) {
                    rate = 100.0;
                } else {
                    rate = 0.0;
                }
            } else {
                rate = new GreedyStrategy(board).calculateHeuristics(playerColor);
            }
        }
        return rate;
    }

}
