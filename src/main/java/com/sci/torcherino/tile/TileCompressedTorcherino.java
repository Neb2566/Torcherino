package com.sci.torcherino.tile;

/**
 * @author sci4me
 */
public class TileCompressedTorcherino extends TileTorcherino
{
    @Override
    protected int speed(final int base)
    {
        return base * 9;
    }
}