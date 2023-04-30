package org.example.strategy;

import org.example.Board;
import org.example.Color;
import org.example.Field;

public interface DecisionStrategy {
    Field bestMove(Color movingColor);
}
