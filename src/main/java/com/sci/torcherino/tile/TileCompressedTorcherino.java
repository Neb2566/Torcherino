package com.sci.torcherino.tile;

public class TileCompressedTorcherino extends TileTorcherino {
    @Override
    protected int speed(final int base) {
        return base * 9;
    }
}
