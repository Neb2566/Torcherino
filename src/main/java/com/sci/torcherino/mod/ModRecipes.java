package com.sci.torcherino.mod;

import com.sci.torcherino.Config;
import com.sci.torcherino.mod.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static void init() {
        if (Config.overpoweredRecipes) {
            GameRegistry.addShapedRecipe(new ResourceLocation("torcherino:tocherino"), null, new ItemStack(ModBlocks.torcherino), new Object[]{
                    " C ",
                    "CTC",
                    " C ",
                    'C', Items.CLOCK,
                    'T', Blocks.TORCH
            });
        } else {
            GameRegistry.addShapedRecipe(new ResourceLocation("torcherino:tocherino"), null, new ItemStack(ModBlocks.torcherino), new Object[]{
                    "SCS",
                    "CTC",
                    "SCS",
                    'S', Items.NETHER_STAR,
                    'C', Items.CLOCK,
                    'T', Blocks.TORCH
            });
        }

        if (Config.compressedTorcherino) {
            GameRegistry.addShapedRecipe(new ResourceLocation("torcherino:compressed_tocherino"), null, new ItemStack(ModBlocks.compressedTorcherino), new Object[]{
                    "TTT",
                    "TTT",
                    "TTT",
                    'T', ModBlocks.torcherino
            });

            if (Config.doubleCompressedTorcherino) {
                GameRegistry.addShapedRecipe(new ResourceLocation("torcherino:double_compressed_tocherino"), null, new ItemStack(ModBlocks.doubleCompressedTorcherino), new Object[]{
                        "TTT",
                        "TTT",
                        "TTT",
                        'T', ModBlocks.compressedTorcherino
                });
            }
        }
    }
}
