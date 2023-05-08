package org.example;

import org.example.decision.AlphaBeta;
import org.example.player.BotPlayer;
import org.example.strategy.CornersStrategy;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class Main {
    public static void main(String[] args) {
        var reversiEngine = new ReversiEngine(WHITE);
        var board = reversiEngine.getBoard();
        reversiEngine.addPlayer(new BotPlayer(BLACK, new CornersStrategy(board), new AlphaBeta(), reversiEngine, 3));
        reversiEngine.addPlayer(new BotPlayer(WHITE, new CornersStrategy(board), new AlphaBeta(), reversiEngine, 5));
        reversiEngine.gameSimulation();
    }
}