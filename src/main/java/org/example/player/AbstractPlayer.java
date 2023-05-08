package org.example.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.engine.Color;
import org.example.engine.Field;

@RequiredArgsConstructor
public abstract class AbstractPlayer {
    @Getter
    protected final Color color;

    public abstract Field makeMove();

    public abstract void onOpponentMoveMade(Field field);
}
