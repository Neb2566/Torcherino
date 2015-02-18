package com.sci.torcherino.block;

import com.sci.torcherino.Torcherino;
import com.sci.torcherino.tile.TileCompressedTorcherino;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author sci4me
 */
public class BlockCompressedTorcherino extends BlockTorcherino
{
    public BlockCompressedTorcherino()
    {
        this.setBlockName("compressed_torcherino");
        this.setBlockTextureName("torcherino:compressed_torcherino" + (Torcherino.animatedTextures ? "_animated" : ""));
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int i)
    {
        return new TileCompressedTorcherino();
    }
}