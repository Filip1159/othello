package org.example.move;

import org.example.Color;
import org.example.Field;

import java.util.List;

public record MoveResult(List<Field> reversedFields, Field startField, Color movingColor) {
    public boolean isValid() {
        return !reversedFields.isEmpty();
    }

    public static MoveResult invalid() {
        return new MoveResult(List.of(), null, null);
    }
}
