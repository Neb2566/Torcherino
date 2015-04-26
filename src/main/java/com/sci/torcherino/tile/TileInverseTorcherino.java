package com.sci.torcherino.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.sci.torcherino.Torcherino;
import com.sci.torcherino.TorcherinoRegistry;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;

import java.util.Random;

/**
 * @author MrComputerGhost
 */
public class TileInverseTorcherino extends TileEntity implements IEnergyHandler {

    private static final String[] MODES = new String[]{"Stopped", "Radius: +1, Area: 3x3x3", "Radius: +2, Area: 5x3x5", "Radius: +3, Area: 7x3x7", "Radius: +4, Area: 9x3x9"};
    private static final int SPEEDS = 4;

    private boolean isActive;
    private byte speed;
    private byte mode;
    private byte cachedMode;
    private Random rand;

    private int xMin;
    private int yMin;
    private int zMin;
    private int xMax;
    private int yMax;
    private int zMax;

    private EnergyStorage redstoneFlux = new EnergyStorage(64);

    public TileInverseTorcherino() {
        this.isActive = true;
        this.cachedMode = -1;
        this.rand = new Random();
    }

    protected int speed(final int base) {
        return base;
    }

    @Override
    public void updateEntity() {
        if (this.worldObj.isRemote)
            return;

        if (!this.isActive || this.mode == 0 || this.speed == 0)
            return;

        if (this.cachedMode != this.mode) {
            this.xMin = this.xCoord - this.mode;
            this.yMin = this.yCoord - 1;
            this.zMin = this.zCoord - this.mode;
            this.xMax = this.xCoord + this.mode;
            this.yMax = this.yCoord + 1;
            this.zMax = this.zCoord + this.mode;
            this.cachedMode = this.mode;
        }

        if ((Torcherino.useRF && redstoneFlux.getEnergyStored() > 1) || (!Torcherino.useRF)) {
            for (int x = this.xMin; x <= this.xMax; x++) {
                for (int y = this.yMin; y <= this.yMax; y++) {
                    for (int z = this.zMin; z <= this.zMax; z++) {
                        final Block block = this.worldObj.getBlock(x, y, z);

                        if (block == null)
                            continue;

                        if (TorcherinoRegistry.isBlockBlacklisted(block))
                            continue;

                        if (block instanceof BlockFluidBase)
                            continue;

                        if (block.getTickRandomly()) {
                            for (int i = 0; i < this.speed; i++) {
                                block.updateTick(this.worldObj, x, y, z, this.rand);
                                if (Torcherino.useRF)
                                    redstoneFlux.setEnergyStored(redstoneFlux.getEnergyStored() - 1);
                            }
                        }

                        if (block.hasTileEntity(this.worldObj.getBlockMetadata(x, y, z))) {
                            final TileEntity tile = this.worldObj.getTileEntity(x, y, z);
                            if (tile != null && !tile.isInvalid()) {
                                if (TorcherinoRegistry.isTileBlacklisted(tile.getClass()))
                                    continue;

                                for (int i = 0; i < this.speed(this.speed); i++) {
                                    if (tile.isInvalid())
                                        break;
                                    tile.updateEntity();
                                    if (Torcherino.useRF)
                                        redstoneFlux.setEnergyStored(redstoneFlux.getEnergyStored() - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void setActive(final boolean active) {
        this.isActive = active;
    }

    public void changeMode(final boolean sneaking) {
        if (sneaking) {
            if (this.speed < TileInverseTorcherino.SPEEDS)
                this.speed++;
            else
                this.speed = 0;
        } else {
            if (this.mode < MODES.length - 1)
                this.mode++;
            else
                this.mode = 0;
        }
    }

    public String getSpeedDescription() {
        return this.speed(this.speed) * 100 + "% increase";
    }

    public String getModeDescription() {
        return TileInverseTorcherino.MODES[this.mode];
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setByte("Speed", this.speed);
        nbt.setByte("Mode", this.mode);
        nbt.setBoolean("IsActive", this.isActive);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.speed = nbt.getByte("Speed");
        this.mode = nbt.getByte("Mode");
        this.isActive = nbt.getBoolean("IsActive");
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return redstoneFlux.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return redstoneFlux.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return redstoneFlux.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return redstoneFlux.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }
}
