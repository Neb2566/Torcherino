package com.sci.torcherino.block;

import com.sci.torcherino.Torcherino;
import com.sci.torcherino.tile.TileDoubleCompressedInverseTorcherino;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author MrComputerGhost
 */
public class BlockDoubleCompressedInverseTorcherino extends BlockInverseTorcherino {

    public BlockDoubleCompressedInverseTorcherino() {
        this.setBlockName("double_compressed_inverse_torcherino");
        this.setBlockTextureName("torcherino:double_compressed_torcherino" + (Torcherino.animatedTextures ? "_animated" : ""));
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int i) {
        return new TileDoubleCompressedInverseTorcherino();
    }
}
