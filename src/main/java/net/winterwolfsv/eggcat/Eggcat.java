package net.winterwolfsv.eggcat;

import net.fabricmc.api.ModInitializer;
import net.winterwolfsv.eggcat.block.CustomBlocks;
import net.winterwolfsv.eggcat.item.CustomItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Eggcat implements ModInitializer {
    public static final String MODID = "eggcat";

    @Override
    public void onInitialize() {
        CustomItems.registerModItems();
        CustomBlocks.registerModBlocks();
    }
}