package com.sci.torcherino.tile;

/**
 * @author MrComputerGhost
 */
public class TileDoubleCompressedTorcherino extends TileTorcherino
{
    @Override
    protected int speed(final int base)
    {
        return base * 81;
    }
}