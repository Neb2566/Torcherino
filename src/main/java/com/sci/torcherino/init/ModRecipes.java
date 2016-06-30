package com.sci.torcherino.init;

import com.sci.torcherino.Torcherino;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModRecipes {
    public static void init() {
        if (Torcherino.overPoweredRecipe) {
            GameRegistry.addRecipe(new ItemStack(ModBlocks.torcherino), "XCX", "CTC", "XCX", 'C', Items.CLOCK, 'T', Blocks.TORCH);
        } else {
            GameRegistry.addRecipe(new ItemStack(ModBlocks.torcherino), "SCS", "CTC", "SCS", 'C', Items.CLOCK, 'S', Items.NETHER_STAR, 'T', Blocks.TORCH);
        }
    }

    private ModRecipes() {
    }
}