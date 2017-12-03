package com.sci.torcherino.block;

import com.sci.torcherino.tile.TileCompressedTorcherino;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCompressedTorcherino extends AbstractTorcherino {
    public BlockCompressedTorcherino() {
        this.setLightLevel(0.75f);
        this.setUnlocalizedName("torcherino.compressed_torcherino");
        this.setRegistryName("compressed_torcherino");
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int i) {
        return new TileCompressedTorcherino();
    }
}
