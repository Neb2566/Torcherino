package com.sci.torcherino.init;

import com.sci.torcherino.Torcherino;
import com.sci.torcherino.block.BlockCompressedTorcherino;
import com.sci.torcherino.block.BlockDoubleCompressedTorcherino;
import com.sci.torcherino.block.BlockTorcherino;
import com.sci.torcherino.tile.TileCompressedTorcherino;
import com.sci.torcherino.tile.TileDoubleCompressedTorcherino;
import com.sci.torcherino.tile.TileTorcherino;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author sci4me
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public final class ModBlocks
{
    public static BlockTorcherino torcherino;
    public static BlockCompressedTorcherino compressedTorcherino;
    public static BlockDoubleCompressedTorcherino doubleCompressedTorcherino;

    public static void init()
    {
        ModBlocks.torcherino = new BlockTorcherino();

        if (Torcherino.compressedTorcherino)
        {
            ModBlocks.compressedTorcherino = new BlockCompressedTorcherino();
            GameRegistry.registerBlock(ModBlocks.compressedTorcherino, ModBlocks.compressedTorcherino.getUnlocalizedName());
            GameRegistry.registerTileEntity(TileCompressedTorcherino.class, "compressed_torcherino_tile");
        }
        if (Torcherino.doubleCompressedTorcherino && Torcherino.compressedTorcherino)
        {
            ModBlocks.doubleCompressedTorcherino = new BlockDoubleCompressedTorcherino();
            GameRegistry.registerBlock(ModBlocks.doubleCompressedTorcherino, ModBlocks.doubleCompressedTorcherino.getUnlocalizedName());
            GameRegistry.registerTileEntity(TileDoubleCompressedTorcherino.class, "double_compressed_torcherino_tile");
        }

        GameRegistry.registerBlock(ModBlocks.torcherino, ModBlocks.torcherino.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileTorcherino.class, "torcherino_tile");
    }

    private ModBlocks()
    {
    }
}