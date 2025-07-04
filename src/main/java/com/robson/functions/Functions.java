package com.robson.functions;


import com.robson.functions.item.ItemRegister;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("functions")
public class Functions {

    public static final String MOD_ID = "functions";

    public Functions(FMLJavaModLoadingContext context) {
        final IEventBus bus = context.getModEventBus();
        ItemRegister.REGISTRY.register(bus);
    }
}