package org.example;

import org.example.decision.MinMax;
import org.example.player.BotPlayer;
import org.example.strategy.CornersStrategy;
import org.example.strategy.GreedyStrategy;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class Main {
    public static void main(String[] args) {
        var reversiEngine = new ReversiEngine();
        var board = reversiEngine.getBoard();
        reversiEngine.addPlayer(new BotPlayer(BLACK, new GreedyStrategy(board), new MinMax(), board, 4));
        reversiEngine.addPlayer(new BotPlayer(WHITE, new CornersStrategy(board), new MinMax(), board, 4));
        reversiEngine.gameSimulation();
    }
}