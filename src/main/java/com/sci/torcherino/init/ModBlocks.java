package com.sci.torcherino.init;

import com.sci.torcherino.Torcherino;
import com.sci.torcherino.block.*;
import com.sci.torcherino.tile.*;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author sci4me
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder("Torcherino")
public final class ModBlocks
{
    public static BlockTorcherino torcherino;
    public static BlockCompressedTorcherino compressedTorcherino;
    public static BlockDoubleCompressedTorcherino doubleCompressedTorcherino;

    public static BlockInverseTorcherino inverseTorcherino;
    public static BlockCompressedInverseTorcherino compressedInverseTorcherino;
    public static BlockDoubleCompressedInverseTorcherino doubleCompressedInverseTorcherino;

    public static void init()
    {
        ModBlocks.torcherino = new BlockTorcherino();

        ModBlocks.inverseTorcherino = new BlockInverseTorcherino();

        if (Torcherino.compressedTorcherino)
        {
            ModBlocks.compressedTorcherino = new BlockCompressedTorcherino();
            GameRegistry.registerBlock(ModBlocks.compressedTorcherino, ModBlocks.compressedTorcherino.getUnlocalizedName());
            GameRegistry.registerTileEntity(TileCompressedTorcherino.class, "compressed_torcherino_tile");

            ModBlocks.compressedInverseTorcherino = new BlockCompressedInverseTorcherino();
            GameRegistry.registerBlock(ModBlocks.compressedInverseTorcherino, ModBlocks.compressedInverseTorcherino.getUnlocalizedName());
            GameRegistry.registerTileEntity(TileCompressedInverseTorcherino.class, "compressed_inverse_torcherino_tile");
        }
        if (Torcherino.doubleCompressedTorcherino && Torcherino.compressedTorcherino)
        {
            ModBlocks.doubleCompressedTorcherino = new BlockDoubleCompressedTorcherino();
            GameRegistry.registerBlock(ModBlocks.doubleCompressedTorcherino, ModBlocks.doubleCompressedTorcherino.getUnlocalizedName());
            GameRegistry.registerTileEntity(TileDoubleCompressedTorcherino.class, "double_compressed_torcherino_tile");

            ModBlocks.doubleCompressedInverseTorcherino = new BlockDoubleCompressedInverseTorcherino();
            GameRegistry.registerBlock(ModBlocks.doubleCompressedInverseTorcherino, ModBlocks.doubleCompressedInverseTorcherino.getUnlocalizedName());
            GameRegistry.registerTileEntity(TileDoubleCompressedInverseTorcherino.class, "double_compressed_inverse_torcherino_tile");
        }

        GameRegistry.registerBlock(ModBlocks.torcherino, ModBlocks.torcherino.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileTorcherino.class, "torcherino_tile");

        GameRegistry.registerBlock(ModBlocks.inverseTorcherino, ModBlocks.inverseTorcherino.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileInverseTorcherino.class, "inverse_torcherino_tile");
    }

    private ModBlocks()
    {
    }
}