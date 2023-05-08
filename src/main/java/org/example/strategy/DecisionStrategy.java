package org.example.strategy;

import org.example.Color;

public interface DecisionStrategy {
    double calculateHeuristics(Color movingColor);
}
