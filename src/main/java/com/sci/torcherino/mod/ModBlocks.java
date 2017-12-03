package com.sci.torcherino.mod;

import com.sci.torcherino.block.AbstractTorcherino;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
    @GameRegistry.ObjectHolder("torcherino:torcherino")
    public static AbstractTorcherino torcherino;

    @GameRegistry.ObjectHolder("torcherino:compressed_torcherino")
    public static AbstractTorcherino compressedTorcherino;

    @GameRegistry.ObjectHolder("torcherino:double_compressed_torcherino")
    public static AbstractTorcherino doubleCompressedTorcherino;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModBlocks.torcherino.initModel();
        ModBlocks.compressedTorcherino.initModel();
        ModBlocks.doubleCompressedTorcherino.initModel();
    }
}
