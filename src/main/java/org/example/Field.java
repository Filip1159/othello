package org.example;

import org.example.move.Direction;

public record Field(int row, int column) {
    public boolean isInsideBoard() {
        return 0 <= row && row < 8 && 0 <= column && column < 8;
    }

    public Field shiftedTowards(Direction direction) {
        return new Field(row + direction.getRowShift(), column + direction.getColumnShift());
    }
}
