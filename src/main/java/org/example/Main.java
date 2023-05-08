package org.example;

import org.example.engine.ReversiEngine;
import org.example.player.bot.BotPlayer;
import org.example.player.bot.decision.MinMax;
import org.example.player.bot.strategy.ManyAvailableMovesStrategy;

import static org.example.engine.Color.BLACK;
import static org.example.engine.Color.WHITE;

public class Main {
    public static void main(String[] args) {
        var reversiEngine = new ReversiEngine(BLACK);
        reversiEngine.addPlayer(new BotPlayer(BLACK, new ManyAvailableMovesStrategy(), new MinMax(), reversiEngine, 3));
        reversiEngine.addPlayer(new BotPlayer(WHITE, new ManyAvailableMovesStrategy(), new MinMax(), reversiEngine, 5));
        reversiEngine.gameSimulation();
    }
}