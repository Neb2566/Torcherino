package com.sci.torcherino.tile;

/**
 * @author MrComputerGhost
 */
public class TileDoubleCompressedInverseTorcherino extends TileInverseTorcherino {

    @Override
    protected int speed(final int base) {
        return base * 81;
    }

}
