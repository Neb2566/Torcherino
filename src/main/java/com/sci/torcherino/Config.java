package com.sci.torcherino;

import com.sci.torcherino.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_BLACKLIST = "blacklist";

    public static boolean logPlacement;
    public static boolean overpoweredRecipes;
    public static boolean compressedTorcherino;
    public static boolean doubleCompressedTorcherino;

    private static String[] blacklistedBlocks;
    private static String[] blacklistedTiles;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            Torcherino.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        // general category
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");

        // feature toggles
        Config.logPlacement = cfg.getBoolean("logPlacement", CATEGORY_GENERAL, false, "(Server owners) Log coordinates of placed Torcherino blocks?");
        Config.overpoweredRecipes = cfg.getBoolean("overpoweredRecipe", CATEGORY_GENERAL, false, "Use the extremely OP Torcherino recipe?");
        Config.compressedTorcherino = cfg.getBoolean("compressedTorcherino", CATEGORY_GENERAL, true, "Is the recipe for Compressed Torcherino enabled?");
        Config.doubleCompressedTorcherino = cfg.getBoolean("doubleCompressedTorcherino", CATEGORY_GENERAL, false, "Is the recipe for Double Compressed Torcherino enabled?");

        // blacklist category
        cfg.addCustomCategoryComment(CATEGORY_BLACKLIST, "Affected blocks blacklist");

        // get blacklists
        Config.blacklistedBlocks = cfg.getStringList("blacklistedBlocks", CATEGORY_BLACKLIST, new String[]{}, "Format: modid:block, modid:block, ...");
        Config.blacklistedTiles = cfg.getStringList("blacklistedTiles", CATEGORY_BLACKLIST, new String[]{}, "Format: tile entity's fully qualified class name");
    }
}
