package org.example.decision;

import org.example.DecisionTree;

public interface DecisionAlgorithm {
    DecisionResult calculate(DecisionTree tree, int level);
}
