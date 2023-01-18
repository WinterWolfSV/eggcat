package net.winterwolfsv.eggcat.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.winterwolfsv.eggcat.Eggcat;
import net.winterwolfsv.eggcat.block.CustomBlocks;

public class CustomItems {
    public static final Item EGGCAT = registerItem("eggcat",
            new FabricItemSettings(), CustomBlocks.EGGCAT);

    //Method to create a new item
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Eggcat.MODID, name), item);
    }

    //Method to create a new block item (takes in Item.setting instead of Item)
    private static Item registerItem(String name, Item.Settings settings, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Eggcat.MODID, name), new BlockItem(block, settings));
    }


    public static void registerModItems() {
    }

}
