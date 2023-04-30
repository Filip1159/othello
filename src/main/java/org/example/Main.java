package org.example;

import org.example.strategy.GreedyStrategy;

import static org.example.Color.WHITE;

public class Main {
    public static void main(String[] args) {
        var board = new Board();
        board.print();
        var greedyStrategy = new GreedyStrategy(board);
        var moveGenerator = greedyStrategy.getMoveGenerator();
        // var moves = moveGenerator.getAvailableMovesOf(WHITE);
        // System.out.println(moves);
        var bestMove = greedyStrategy.bestMove(WHITE);

    }
}