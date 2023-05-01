package org.example;

import org.example.player.BotPlayer;
import org.example.strategy.GreedyStrategy;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class Main {
    public static void main(String[] args) {
        var reversiEngine = new ReversiEngine();
        var board = reversiEngine.getBoard();
        reversiEngine.addPlayer(new BotPlayer(WHITE, new GreedyStrategy(board), board));
        reversiEngine.addPlayer(new BotPlayer(BLACK, new GreedyStrategy(board), board));
        reversiEngine.gameSimulation();
    }
}