package com.robson.functions.utils;

import com.robson.functions.item.FunctionVisualizerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DrawFunction {

    public static ConcurrentHashMap<Player, List<Character>> functions = new ConcurrentHashMap<>();

    private static final byte size = 10;

    private static final byte interpolations = 5;

    public static void draw(Minecraft client){
        if (functions.get(client.player) != null && !functions.get(client.player).isEmpty() && client.player.getMainHandItem().getItem() instanceof FunctionVisualizerItem && client.player.tickCount % 10 == 0) {
            for (byte i = -size * interpolations; i < size * interpolations; i++) {
                float x = (float) i / interpolations;
                FunctionOutPut outPut = CalculateFunction.calculate(x, functions.get(client.player));
                Vec3 pos = new Vec3(0 + x, 0 + outPut.realpart(), outPut.imaginarypart());
                client.particleEngine.createParticle(ParticleTypes.FLAME, pos.x, pos.y, pos.z, 0, 0, 0).scale((float) (2 + (client.player.distanceToSqr(pos) * 0.001)));
            }
        }
    }
}
