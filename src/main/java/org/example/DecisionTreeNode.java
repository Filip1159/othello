package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.move.MoveGenerator;
import org.example.strategy.DecisionStrategy;

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
    private final Field moveMade;
    @Getter
    private boolean isLeaf = false;
    private Double rate = null;
    private final DecisionStrategy decisionStrategy;

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
                    var child = new DecisionTreeNode(playerColor.opposite(), boardCopy, new MoveGenerator(boardCopy),
                            move.startField(), decisionStrategy);
                    children.add(child);
                    child.expand(level - 1);
                }
            } else {
                var opponentMoves = moveGenerator.getAvailableMovesOf(playerColor.opposite());
                if (opponentMoves.isEmpty()) {
                    isLeaf = true;
                } else {
                    var boardCopy = board.copy();
                    var node = new DecisionTreeNode(playerColor.opposite(), boardCopy, new MoveGenerator(boardCopy),
                            null, decisionStrategy);
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
                rate = decisionStrategy.calculateHeuristics(playerColor);
            }
        }
        return rate;
    }

    public DecisionTreeNode getChildByMoveMade(Field field) {
        return children.stream()
                .filter(child -> child.moveMade.equals(field))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No child with such move made exist: " + field));
    }
}
