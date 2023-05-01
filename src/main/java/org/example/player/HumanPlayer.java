package org.example.player;

import org.example.Color;
import org.example.Field;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(Color color) {
        super(color);
    }

    @Override
    public Field makeMove() {
        try {
            System.out.println("Select field (row, column) to place your " + color + " disk:");
            var row = scanner.nextInt();
            var column = scanner.nextInt();
            var field = new Field(row, column);
            if (!field.isInsideBoard())
                throw new IllegalArgumentException();
            else
                return field;
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Incorrect answer! Try again!");
            return makeMove();
        }
    }

    @Override
    public void onOpponentMoveMade(Field field) {
        System.out.println("Opponent made move: " + field);
    }
}
