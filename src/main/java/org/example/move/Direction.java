package org.example.move;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {
    UP(0, -1),
    UP_RIGHT(1, -1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, 1),
    DOWN(0, 1),
    LEFT_DOWN(-1, 1),
    LEFT(-1, 0),
    UP_LEFT(-1, -1);

    @Getter
    private final int rowShift, columnShift;
}
