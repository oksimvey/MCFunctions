package com.robson.functions.events;

import com.robson.functions.screen.FunctionScreen;
import com.robson.functions.utils.DrawFunction;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class ClientEvents {

    @SubscribeEvent
    public static void onClientTick (TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player != null){
            DrawFunction.draw(Minecraft.getInstance());
        }
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event){
        DrawFunction.functions.computeIfAbsent(event.getEntity(), k -> new ArrayList<>());
        Minecraft.getInstance().setScreen(new FunctionScreen());
    }
}
