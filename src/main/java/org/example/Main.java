package org.example;

import org.example.decision.AlphaBeta;
import org.example.decision.MinMax;
import org.example.player.BotPlayer;
import org.example.strategy.CornersStrategy;
import org.example.strategy.GreedyStrategy;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class Main {
    public static void main(String[] args) {
        var reversiEngine = new ReversiEngine(BLACK);
        var board = reversiEngine.getBoard();
        reversiEngine.addPlayer(new BotPlayer(BLACK, new CornersStrategy(board), new MinMax(), reversiEngine, 7));
        reversiEngine.addPlayer(new BotPlayer(WHITE, new GreedyStrategy(board), new AlphaBeta(), reversiEngine, 2));
        reversiEngine.gameSimulation();
    }
}