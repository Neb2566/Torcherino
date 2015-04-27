package com.sci.torcherino.block;

import com.sci.torcherino.Torcherino;
import com.sci.torcherino.tile.TileDoubleCompressedTorcherino;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author MrComputerGhost
 */
public class BlockDoubleCompressedTorcherino extends BlockTorcherino
{
    public BlockDoubleCompressedTorcherino()
    {
        this.setBlockName("double_compressed_torcherino");
        this.setLightLevel(0.75f);
        this.setBlockTextureName("torcherino:double_compressed_torcherino" + (Torcherino.animatedTextures ? "_animated" : ""));
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int i)
    {
        return new TileDoubleCompressedTorcherino();
    }
}