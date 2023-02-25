package net.winterwolfsv.eggcat.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.winterwolfsv.eggcat.Eggcat;
import net.winterwolfsv.eggcat.block.custom.EggcatBlock;

public class CustomBlocks {
    public static final Block EGGCAT = registerBlock("eggcat", new EggcatBlock(FabricBlockSettings.of(Material.METAL).strength(0.4f)));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Eggcat.MODID, name), block);
    }
    
    public static void registerModBlocks() {
    }
}
