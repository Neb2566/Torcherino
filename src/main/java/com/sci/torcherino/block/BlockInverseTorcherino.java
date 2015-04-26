package com.sci.torcherino.block;

import com.sci.torcherino.Torcherino;
import com.sci.torcherino.tile.TileInverseTorcherino;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

/**
 * @author MrComputerGhost
 */
public class BlockInverseTorcherino extends BlockTorch implements ITileEntityProvider {

    public BlockInverseTorcherino() {
        this.setBlockName("inverse_torcherino");
        this.setLightLevel(0.75f);
        this.setBlockTextureName("torcherino:torcherino" + (Torcherino.animatedTextures ? "_animated" : ""));
    }

    @Override
    public void onBlockAdded(final World world, final int x, final int y, final int z) {
        if (!world.isRemote) {
            final TileEntity tile = world.getTileEntity(x, y, z);

            if (tile != null && tile instanceof TileInverseTorcherino) {
                ((TileInverseTorcherino) tile).setActive(world.isBlockIndirectlyGettingPowered(x, y, z));
            }
        }

        super.onBlockAdded(world, x, y, z);
        if (Torcherino.logPlacement)
            Torcherino.logger.log(Level.INFO, "Inverted Torcherino was placed at " + x + ", " + y + ", " + z);
    }

    @Override
    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block block) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);

            if (tile != null && tile instanceof TileInverseTorcherino) {
                ((TileInverseTorcherino) tile).setActive(world.isBlockIndirectlyGettingPowered(x, y, z));
            }
        }

        super.onNeighborBlockChange(world, x, y, z, block);
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int par1, final float par2, final float par3, final float par4) {
        if (!world.isRemote) {
            final TileEntity tile = world.getTileEntity(x, y, z);

            if (tile == null || !(tile instanceof TileInverseTorcherino)) {
                return false;
            }

            final TileInverseTorcherino torch = (TileInverseTorcherino) tile;

            torch.changeMode(player.isSneaking());

            if (player.isSneaking()) {
                player.addChatComponentMessage(new ChatComponentText("Changed speed: " + torch.getSpeedDescription()));
            } else {
                player.addChatComponentMessage(new ChatComponentText("Changed mode: " + torch.getModeDescription()));
            }
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int i) {
        return new TileInverseTorcherino();
    }

}
