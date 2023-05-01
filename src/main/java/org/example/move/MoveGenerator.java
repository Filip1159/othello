package org.example.move;

import lombok.RequiredArgsConstructor;
import org.example.Board;
import org.example.Color;
import org.example.Field;

import java.util.ArrayList;

@RequiredArgsConstructor
public class MoveGenerator {
    private final Board board;

    public ArrayList<MoveResult> getAvailableMovesOf(Color color) {
        var availableMoves = new ArrayList<MoveResult>();
        for (var row = 0; row < 8; row++)
            for (var column = 0; column < 8; column++) {
                var startField = new Field(row, column);
                var moveResult = performMoveSimulation(startField, color);
                if (moveResult.isValid())
                    availableMoves.add(moveResult);
            }
        return availableMoves;
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
        return new MoveResult(reversedFields, field, color);
    }
}
