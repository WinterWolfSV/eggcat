package net.winterwolfsv.eggcat.item;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.winterwolfsv.eggcat.Eggcat;
import net.winterwolfsv.eggcat.block.CustomBlocks;

public class CustomItems {
    public static final Item EGGCAT = registerItem(new FabricItemSettings());


    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(Items.DRAGON_EGG, EGGCAT));
    }

    private static Item registerItem(Item.Settings settings) {
        return Registry.register(Registries.ITEM, new Identifier(Eggcat.MODID, "eggcat"), new BlockItem(CustomBlocks.EGGCAT, settings));
    }

    public static void registerModItems() {
        registerItems();
    }

}
