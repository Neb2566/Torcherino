package com.sci.torcherino;

import com.sci.torcherino.mod.ModBlocks;
import com.sci.torcherino.proxy.CommonProxy;
import com.sci.torcherino.tile.TileCompressedTorcherino;
import com.sci.torcherino.tile.TileDoubleCompressedTorcherino;
import com.sci.torcherino.tile.TileTorcherino;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = "torcherino", name = "Torcherino", version = "@VERSION@", dependencies = "required-after:forge@14.23.1.2555", useMetadata = true)
public final class Torcherino {

    private static Torcherino instance;

    @Mod.InstanceFactory
    public static Torcherino isntance() {
        if (Torcherino.instance == null) {
            Torcherino.instance = new Torcherino();
        }
        return Torcherino.instance;
    }

    @SidedProxy(clientSide = "com.sci.torcherino.proxy.ClientProxy", serverSide = "com.sci.torcherino.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        // Start loggger
        Torcherino.logger = e.getModLog();

        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        TorcherinoRegistry.blacklistBlock(Blocks.AIR);

        TorcherinoRegistry.blacklistBlock(ModBlocks.torcherino);
        TorcherinoRegistry.blacklistBlock(ModBlocks.compressedTorcherino);
        TorcherinoRegistry.blacklistBlock(ModBlocks.doubleCompressedTorcherino);

        TorcherinoRegistry.blacklistTile(TileTorcherino.class);
        TorcherinoRegistry.blacklistTile(TileCompressedTorcherino.class);
        TorcherinoRegistry.blacklistTile(TileDoubleCompressedTorcherino.class);

        TorcherinoRegistry.blacklistBlock(Blocks.WATER);
        TorcherinoRegistry.blacklistBlock(Blocks.FLOWING_WATER);

        TorcherinoRegistry.blacklistBlock(Blocks.LAVA);
        TorcherinoRegistry.blacklistBlock(Blocks.FLOWING_LAVA);

        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void imcMessage(final FMLInterModComms.IMCEvent evt) {
        for (final FMLInterModComms.IMCMessage message : evt.getMessages()) {
            if (!message.isStringMessage()) {
                System.out.println("Received non-string message! Ignoring");
                continue;
            }

            final String s = message.getStringValue();

            if (message.key.equals("blacklist-block"))
                this.blacklistBlock(s);
            else if (message.key.equals("blacklist-tile"))
                this.blacklistTile(s);
        }
    }

    private void blacklistBlock(final String s) {
        final String[] parts = s.split(":");

        if (parts.length != 2) {
            System.out.println("Received malformed message: " + s);
            return;
        }

        final Block block = Block.REGISTRY.getObject(new ResourceLocation(parts[0], parts[1]));

        if (block == null) {
            System.out.println("Could not find block: " + s + ", ignoring");
            return;
        }

        System.out.println("Blacklisting block: " + block.getUnlocalizedName());

        TorcherinoRegistry.blacklistBlock(block);
    }

    @SuppressWarnings("unchecked")
    private void blacklistTile(final String s) {
        try {
            final Class<?> clazz = this.getClass().getClassLoader().loadClass(s);

            if (clazz == null) {
                System.out.println("Class null: " + s);
                return;
            }

            if (!TileEntity.class.isAssignableFrom(clazz)) {
                System.out.println("Class not a TileEntity: " + s);
                return;
            }

            TorcherinoRegistry.blacklistTile((Class<? extends TileEntity>) clazz);
        } catch (final ClassNotFoundException e) {
            System.out.println("Class not found: " + s + ", ignoring");
        }
    }
}
