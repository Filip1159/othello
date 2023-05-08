package org.example;

import org.example.engine.ReversiEngine;
import org.example.player.bot.decision.MinMax;
import org.example.player.bot.BotPlayer;
import org.example.player.bot.strategy.CornersStrategy;
import org.example.player.bot.strategy.GreedyStrategy;

import static org.example.engine.Color.BLACK;
import static org.example.engine.Color.WHITE;

public class Main {
    public static void main(String[] args) {
        var reversiEngine = new ReversiEngine(WHITE);
        var board = reversiEngine.getBoard();
        reversiEngine.addPlayer(new BotPlayer(BLACK, new GreedyStrategy(board), new MinMax(), reversiEngine, 3));
        reversiEngine.addPlayer(new BotPlayer(WHITE, new CornersStrategy(board), new MinMax(), reversiEngine, 5));
        reversiEngine.gameSimulation();
    }
}