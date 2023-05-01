package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.strategy.DecisionStrategy;

@Data
@AllArgsConstructor
public class Player {
    private final Color color;
    private DecisionStrategy strategy;

    public double calculateHeuristics(Board board) {
        return strategy.calculateHeuristics(color);
    }
}
