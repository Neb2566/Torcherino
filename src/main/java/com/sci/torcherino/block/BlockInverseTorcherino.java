package com.sci.torcherino.block;

import com.sci.torcherino.Torcherino;
import com.sci.torcherino.tile.TileInverseTorcherino;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author MrComputerGhost
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockInverseTorcherino extends BlockTorcherino
{
    public BlockInverseTorcherino()
    {
        this.setBlockName("inverse_torcherino");
        this.setLightLevel(0.75f);
        this.setBlockTextureName("torcherino:torcherino" + (Torcherino.animatedTextures ? "_animated" : ""));
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int i)
    {
        return new TileInverseTorcherino();
    }
}