package org.example;

import lombok.Data;
import org.example.strategy.DecisionStrategy;

@Data
public class Player {
    private final Color color;
    private Player opponent;
    private DecisionStrategy strategy;
}
