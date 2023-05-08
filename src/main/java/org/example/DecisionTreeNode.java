package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.move.MoveGenerator;
import org.example.strategy.DecisionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class DecisionTreeNode {
    @Getter
    private final Color moveMadeBy;
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

    private boolean expanded = false;

    public void expand(int level) {
        if (level <= 0) return;

        if (!expanded || children.isEmpty()) {
            var availableChildrenMoves = moveGenerator.getAvailableMovesOf(moveMadeBy.opposite());
            expanded = true;

            if (!availableChildrenMoves.isEmpty()) {
                for (var move : availableChildrenMoves) {
                    var boardCopy = board.copy();
                    boardCopy.applyMove(move);
                    var child = new DecisionTreeNode(moveMadeBy.opposite(), boardCopy, new MoveGenerator(boardCopy),
                            move.startField(), decisionStrategy);
                    children.add(child);
                    child.expand(level - 1);
                }
            } else {
                var opponentMoves = moveGenerator.getAvailableMovesOf(moveMadeBy);
                if (opponentMoves.isEmpty()) {
                    isLeaf = true;
                } else {
                    var boardCopy = board.copy();
                    var node = new DecisionTreeNode(moveMadeBy.opposite(), boardCopy, new MoveGenerator(boardCopy),
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
                var playerDisks = board.getNumberOfFieldsOf(moveMadeBy);
                var opponentDisks = board.getNumberOfFieldsOf(moveMadeBy.opposite());

                if (playerDisks > opponentDisks) {
                    rate = Double.MAX_VALUE;
                } else {
                    rate = -Double.MAX_VALUE;
                }
            } else {
                rate = decisionStrategy.calculateHeuristics(moveMadeBy);
            }
        }
        return rate;
    }

    public DecisionTreeNode getChildByMoveMade(Field field) {
        return children.stream()
                .filter(child -> Objects.equals(child.moveMade, field))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No child with such move made exist: " + field));
    }
}
