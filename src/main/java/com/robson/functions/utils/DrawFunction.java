package com.robson.functions.utils;

import com.robson.functions.item.FunctionVisualizerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DrawFunction {

    public static ConcurrentHashMap<Player, List<Character>> functions = new ConcurrentHashMap<>();

    public static boolean lock = false;

    public static byte size = 10;

    private static final byte interpolations = 5;

    public static int distance = 30;

    private static Vec3 fixedpos = new Vec3(0, 0, 0);

    private static float lastrot = 0;

    public static void draw(Minecraft client){
        if (!lock){
            fixedpos = client.player.position();
            lastrot = client.player.getYRot();
        }
        if (functions.get(client.player) != null && !functions.get(client.player).isEmpty() && client.player.getMainHandItem().getItem() instanceof FunctionVisualizerItem && client.player.tickCount % 10 == 0) {
            for (byte i = (byte) (-size * interpolations); i < size * interpolations; i++) {
                float x = (float) i / interpolations;
                FunctionOutPut outPut = CalculateFunction.calculate(-(x), functions.get(client.player));
                Vec3 pos = fixedpos.add(0, (outPut.realNumber().value()), 0);
                Vec2f rotatedoutput =  new Vec2f(x, (-(outPut.complexNumber().value()))).rotate(lastrot);
                Vec2f rotatedoffset = new Vec2f(distance, distance).rotate(lastrot + 45);
                Vec2f rot = rotatedoutput.add(rotatedoffset);
                Particle particle = client.particleEngine.createParticle(ParticleTypes.FLAME, pos.x + rot.x(), pos.y, pos.z + rot.y(), 0, 0, 0);
                if (particle != null) {
                    particle.setLifetime(10);
                    particle.scale((float) (2 + (client.player.distanceToSqr(pos) * 0.001)));
                }
            }
        }
    }
}
