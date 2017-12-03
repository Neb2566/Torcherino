package com.sci.torcherino.block;

import com.sci.torcherino.Config;
import com.sci.torcherino.Torcherino;
import com.sci.torcherino.tile.TileTorcherino;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

public abstract class AbstractTorcherino extends BlockTorch implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }

    @Override
    public void onBlockAdded(final World world, BlockPos pos, final IBlockState state) {
        if (!world.isRemote) {
            final TileEntity tile = world.getTileEntity(pos);

            if (tile != null && tile instanceof TileTorcherino) {
                ((TileTorcherino) tile).setPoweredByRedstone(world.isBlockIndirectlyGettingPowered(pos) > 0);
            }
        }

        super.onBlockAdded(world, pos, state);

        // log placement if set in config
        if (Config.logPlacement) {
            Torcherino.logger.log(Level.INFO, "Torcherino was placed at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
        }
    }

    @Override
    public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block block, final BlockPos fromPos) {
        if (!world.isRemote) {
            final TileEntity tile = world.getTileEntity(pos);

            if (tile != null && tile instanceof TileTorcherino) {
                ((TileTorcherino) tile).setPoweredByRedstone(world.isBlockIndirectlyGettingPowered(pos) > 0);
            }
        }

        super.neighborChanged(state, world, pos, block, fromPos);
    }

    @Override
    public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
        if (!world.isRemote) {
            final TileEntity tile = world.getTileEntity(pos);

            if (hand != EnumHand.MAIN_HAND)
                return false;

            if (tile == null || !(tile instanceof TileTorcherino))
                return false;

            final TileTorcherino torch = (TileTorcherino) tile;

            torch.changeMode(player.isSneaking());

            if (player.isSneaking()) {
                player.sendMessage(new TextComponentString("Changed speed: " + torch.getSpeedDescription()));
            } else {
                player.sendMessage(new TextComponentString("Changed mode: " + torch.getModeDescription()));
            }
        }

        return false;
    }
}
