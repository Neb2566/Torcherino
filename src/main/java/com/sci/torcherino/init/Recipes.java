package com.sci.torcherino.init;

import com.sci.torcherino.Torcherino;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author sci4me
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public final class Recipes
{
    public static void init()
    {
        if (Torcherino.overPoweredRecipe)
            GameRegistry.addRecipe(new ItemStack(ModBlocks.torcherino), new Object[]{"XCX", "CTC", "XCX", 'C', Items.clock, 'T', Blocks.torch});
        else
            GameRegistry.addRecipe(new ItemStack(ModBlocks.torcherino), new Object[]{"SCS", "CTC", "SCS", 'C', Items.clock, 'S', Items.nether_star, 'T', Blocks.torch});

        if (Torcherino.compressedTorcherino)
        {
            GameRegistry.addRecipe(new ItemStack(ModBlocks.compressedTorcherino), new Object[]{"ttt","ttt","ttt", 't', ModBlocks.torcherino});
        }
    }

    private Recipes()
    {
    }
}