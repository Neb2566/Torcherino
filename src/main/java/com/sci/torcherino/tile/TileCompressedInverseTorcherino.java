package com.sci.torcherino.tile;

/**
 * @author MrComputerGhost
 */
public class TileCompressedInverseTorcherino extends TileInverseTorcherino {

    @Override
    protected int speed(final int base)
    {
        return base * 9;
    }

}
