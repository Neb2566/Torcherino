package com.sci.torcherino.proxy;

import com.sci.torcherino.Config;
import com.sci.torcherino.mod.ModBlocks;
import com.sci.torcherino.mod.ModRecipes;
import com.sci.torcherino.block.BlockCompressedTorcherino;
import com.sci.torcherino.block.BlockDoubleCompressedTorcherino;
import com.sci.torcherino.block.BlockTorcherino;
import com.sci.torcherino.tile.TileCompressedTorcherino;
import com.sci.torcherino.tile.TileDoubleCompressedTorcherino;
import com.sci.torcherino.tile.TileTorcherino;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

@Mod.EventBusSubscriber
public abstract class CommonProxy {

    // Config instance
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        // Configuration handler
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "torcherino.cfg"));
        Config.readConfig();
    }

    public void init(FMLInitializationEvent e) {
        ModRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockTorcherino());
        GameRegistry.registerTileEntity(TileTorcherino.class, "torcherino_torcherino");

        event.getRegistry().register(new BlockCompressedTorcherino());
        GameRegistry.registerTileEntity(TileCompressedTorcherino.class, "torcherino_compressed_torcherino");

        event.getRegistry().register(new BlockDoubleCompressedTorcherino());
        GameRegistry.registerTileEntity(TileDoubleCompressedTorcherino.class, "torcherino_double_compressed_torcherino");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.torcherino).setRegistryName(ModBlocks.torcherino.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.compressedTorcherino).setRegistryName(ModBlocks.compressedTorcherino.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.doubleCompressedTorcherino).setRegistryName(ModBlocks.doubleCompressedTorcherino.getRegistryName()));
    }
}
