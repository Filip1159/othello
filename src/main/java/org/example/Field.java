package org.example;

import org.example.move.Direction;

public record Field(int row, int column) {
    public boolean isInsideBoard() {
        return 0 <= row && row < 8 && 0 <= column && column < 8;
    }

    public Field shiftedTowards(Direction direction) {
        return new Field(row + direction.getRowShift(), column + direction.getColumnShift());
    }

    @Override
    public String toString() {
        return "[" + row + ", " + column + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Field otherField)) return false;
        return otherField.column == column && otherField.row == row;
    }
}
