package com.sci.torcherino.init;

import com.sci.torcherino.block.BlockTorcherino;
import com.sci.torcherino.tile.TileTorcherino;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

@GameRegistry.ObjectHolder("Torcherino")
public final class ModBlocks {
    public static BlockTorcherino torcherino;

    private static Map<String, ItemBlock> remap = new HashMap<String, ItemBlock>();

    public static void init() {
        ModBlocks.torcherino = new BlockTorcherino();
        GameRegistry.register(ModBlocks.torcherino.setRegistryName("BlockTorcherino"));
        GameRegistry.register(new ItemBlock(ModBlocks.torcherino).setRegistryName(ModBlocks.torcherino.getRegistryName()));
        GameRegistry.registerTileEntity(TileTorcherino.class, "torcherino_tile");
        ModBlocks.remap.put("torcherino:tile.torcherino", (ItemBlock) Item.getItemFromBlock(ModBlocks.torcherino));
    }

    public static void initRenders() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.torcherino), 0, new ModelResourceLocation(new ResourceLocation("torcherino", "BlockTorcherino"), "inventory"));
    }

    public static void handleMissingMappings(final FMLMissingMappingsEvent event) {
        for (FMLMissingMappingsEvent.MissingMapping mapping : event.get()) {
            if (ModBlocks.remap.containsKey(mapping.name)) {
                if (mapping.type == GameRegistry.Type.ITEM)
                    mapping.remap(ModBlocks.remap.get(mapping.name));
                else
                    mapping.remap(ModBlocks.remap.get(mapping.name).getBlock());
            }
        }
    }

    private ModBlocks() {
    }
}