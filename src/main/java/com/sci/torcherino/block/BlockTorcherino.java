package com.sci.torcherino.block;

import com.sci.torcherino.tile.TileTorcherino;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTorcherino extends AbstractTorcherino {
    public BlockTorcherino() {
        this.setLightLevel(0.75f);
        this.setUnlocalizedName("torcherino.torcherino");
        this.setRegistryName("torcherino");
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int i) {
        return new TileTorcherino();
    }
}
