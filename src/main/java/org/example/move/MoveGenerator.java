package org.example.move;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.Board;
import org.example.Color;
import org.example.Field;

import java.util.ArrayList;

@RequiredArgsConstructor
public class MoveGenerator {
    private final Board board;

    public ArrayList<Field> getAvailableMovesOf(Color color) {
        var availableMoves = new ArrayList<Field>();
        for (var row = 0; row < 7; row++)
            for (var column = 0; column < 7; column++) {
                var field = new Field(row, column);
                if (isLegalMove(field, color))
                    availableMoves.add(field);
            }
        return availableMoves;
    }

    private boolean isLegalMove(Field field, Color color) {
        return performMoveSimulation(field, color).isValid();
    }

    private MoveResult performMoveSimulation(Field field, Color color) {
        if (board.colorAt(field) != null) {
            return MoveResult.invalid();
        }
        var reversedFields = new ArrayList<Field>();
        for (var direction : Direction.values()) {
            var metMyColorFieldOnLineEnd = false;
            var metNullField = false;
            var currentField = field.shiftedTowards(direction);
            var oppositesOnLine = new ArrayList<Field>();
            while (currentField.isInsideBoard() && !metNullField && !metMyColorFieldOnLineEnd) {
//                System.out.println(currentField);
                if (color.opposite().equals(board.colorAt(currentField))) {
                    oppositesOnLine.add(currentField);
                    currentField = currentField.shiftedTowards(direction);
                } else if (color.equals(board.colorAt(currentField))) {
                    metMyColorFieldOnLineEnd = true;
                } else {
                    metNullField = true;
                }
            }
            if (!oppositesOnLine.isEmpty() && metMyColorFieldOnLineEnd)
                reversedFields.addAll(oppositesOnLine);
        }
        return new MoveResult(reversedFields);
    }
}
