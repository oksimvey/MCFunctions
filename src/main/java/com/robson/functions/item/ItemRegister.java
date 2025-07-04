package com.robson.functions.item;

import com.robson.functions.Functions;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegister {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Functions.MOD_ID);

    public static final RegistryObject<Item> FUNCTION_ITEM = REGISTRY.register("function", FunctionVisualizerItem::new);

}