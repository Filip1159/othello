package org.example;


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
                if (field.equals(color))
                    result++;
        return result;
    }

    public void setColorAt(Field field, Color color) {
        board[field.row()][field.column()] = color;
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
}
