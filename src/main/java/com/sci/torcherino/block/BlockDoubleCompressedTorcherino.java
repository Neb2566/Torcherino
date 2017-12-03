package com.sci.torcherino.block;

import com.sci.torcherino.tile.TileDoubleCompressedTorcherino;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDoubleCompressedTorcherino extends AbstractTorcherino {
    public BlockDoubleCompressedTorcherino() {
        this.setLightLevel(0.75f);
        this.setUnlocalizedName("torcherino.double_compressed_torcherino");
        this.setRegistryName("double_compressed_torcherino");
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int i) {
        return new TileDoubleCompressedTorcherino();
    }
}
