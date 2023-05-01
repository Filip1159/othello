package org.example;


import org.example.move.MoveResult;

import static org.example.Color.BLACK;
import static org.example.Color.WHITE;

public class Board {
    private final Color[][] board = new Color[8][8];

    public Board() {
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
    }

    public int getNumberOfFieldsOf(Color color) {
        var result = 0;
        for (var row : board)
            for (var field : row)
                if (color.equals(field))
                    result++;
        return result;
    }

    public Color colorAt(Field field) {
        return board[field.row()][field.column()];
    }

    public void print() {
        for (var row = 0; row < 8; row++) {
            for (var column = 0; column < 8; column++) {
                if (BLACK.equals(board[row][column])) {
                    System.out.print("O ");
                } else if (WHITE.equals(board[row][column])) {
                    System.out.print("@ ");
                } else {
                    System.out.print("x ");
                }
            }
            System.out.println();
        }
    }

    public Board copy() {
        var result = new Board();
        for (int row = 0; row < 8; row++)
            for (int column = 0; column < 8; column++)
                result.board[row][column] = this.board[row][column];
        return result;
    }

    public void applyMove(MoveResult moveResult) {
        validateMoveBeforeApplying(moveResult);
        setColorAt(moveResult.startField(), moveResult.movingColor());
        moveResult.reversedFields().forEach(reversedField -> setColorAt(reversedField, moveResult.movingColor()));
    }

    private void validateMoveBeforeApplying(MoveResult moveResult) {
        if (!moveResult.isValid())
            throw new IllegalArgumentException("Move result must be valid!");
        if (colorAt(moveResult.startField()) != null)
            throw new IllegalArgumentException("Start field must be empty!");
        moveResult.reversedFields().forEach(reversedField -> {
            if (!colorAt(reversedField).equals(moveResult.movingColor().opposite()))
                throw new IllegalStateException("One of reversed fields: " + reversedField + " has color: " +
                        colorAt(reversedField) + ", but it has to be " + moveResult.movingColor().opposite());
        });
    }

    private void setColorAt(Field field, Color color) {
        board[field.row()][field.column()] = color;
    }
}
