package org.example.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.Color;
import org.example.Field;

@RequiredArgsConstructor
public abstract class AbstractPlayer {
    @Getter
    protected final Color color;

    public abstract Field makeMove();

    public abstract void onOpponentMoveMade(Field field);
}
