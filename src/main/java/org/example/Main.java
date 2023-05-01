package org.example;

import org.example.strategy.GreedyStrategy;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class Main {
    public static void main(String[] args) {
        var board = new Board();
        board.print();
        var whitePlayer = new Player(WHITE, new GreedyStrategy(board));
        var blackPlayer = new Player(BLACK, new GreedyStrategy(board));

        var decisionTree = new DecisionTree(board, WHITE);

        var level = 6;

        while (true) {
            if (decisionTree.isLeaf())
                break;

            decisionTree.expand(level);

            var maxValue = MinMax.calculate(decisionTree, level);

            for (var child : decisionTree.getRoot().getChildren()) {
                if (child.getMinmax() == maxValue) {
                    decisionTree.setRoot(child);
                }
            }

            System.out.println("WHITE move");
            decisionTree.getRoot().getBoard().print();
            System.out.println();

            if (decisionTree.isLeaf())
                break;

            // player 2
            var bestMoveRate = Double.MIN_VALUE;
            var nextMove = 0;

            for (int childNumber = 0; childNumber < decisionTree.getRoot().getChildren().size(); childNumber++) {
                var child = decisionTree.getRoot().getChildren().get(childNumber);
                var moveRate = blackPlayer.calculateHeuristics(board);

                if (moveRate > bestMoveRate) {
                    bestMoveRate = moveRate;
                    nextMove = childNumber;
                }
            }

            decisionTree.setRoot(decisionTree.getRoot().getChildren().get(nextMove));

            System.out.println("BLACK move");
            decisionTree.getRoot().getBoard().print();
            System.out.println();
        }

        var resultBoard = decisionTree.getRoot().getBoard();
        resultBoard.print();
        System.out.println(resultBoard.getNumberOfFieldsOf(WHITE));
        System.out.println(resultBoard.getNumberOfFieldsOf(BLACK));
    }
}