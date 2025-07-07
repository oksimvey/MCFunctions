package com.robson.functions.screen;

import com.robson.functions.utils.CalculateFunction;
import com.robson.functions.utils.DrawFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class FunctionScreen extends Screen {

    private static final Component component = Component.literal("Function Screen");

   public FunctionScreen() {
        super(component);
        this.minecraft = Minecraft.getInstance();
        this.font = this.minecraft.font;
    }

    public boolean isPauseScreen() {
       return false;
    }

    public void init(){
       super.init();
        this.addRenderableWidget(Button.builder(Component.literal("toggle"), button -> toggle())
                .bounds((int) ((double) this.width / 2 + (10 * 12.5)), this.height / 2 + 10, 50, 10)
                .build());

        this.addRenderableWidget(Button.builder(Component.literal("increase"), button -> increase())
                .bounds(this.width / 2 + (10 * 10), this.height / 2, 50, 10)
                .build());
        this.addRenderableWidget(Button.builder(Component.literal("decrease"), button -> decrease())
                .bounds(this.width / 2 + (10 * 15), this.height / 2, 50, 10)
                .build());
        this.addRenderableWidget(Button.builder(Component.literal("afast"), button -> afast())
                .bounds(this.width / 2 + (10 * 10), this.height / 2 - 10, 50, 10)
                .build());
        this.addRenderableWidget(Button.builder(Component.literal("aproximate"), button -> aproximate())
                .bounds(this.width / 2 + (10 * 15), this.height / 2 - 10, 50, 10)
                .build());
        for (int i = 0; i < CalculateFunction.validBackFunctions.size(); i++){
            Character c = CalculateFunction.validBackFunctions.get(i);
            this.addRenderableWidget(Button.builder(Component.literal(String.valueOf(c)), button -> add(c))
                    .bounds(this.width / 2 + (i * 10), this.height / 2 - 20, 10, 10)
                    .build());
        }
       for (int i = 0; i < CalculateFunction.validNumbers.size(); i++){
           Character c = CalculateFunction.validNumbers.get(i);
           this.addRenderableWidget(Button.builder(Component.literal(String.valueOf(c)), button -> add(c))
                   .bounds(this.width / 2 + (i * 10), this.height / 2 - 30, 10, 10)
                   .build());
       }
        for (int i = 0; i < CalculateFunction.validOperators.size(); i++){
            Character c = CalculateFunction.validOperators.get(i);
            this.addRenderableWidget(Button.builder(Component.literal(String.valueOf(c)), button -> add(c))
                    .bounds(this.width / 2 + (i * 10), this.height / 2 - 40, 10, 10)
                    .build());
        }
        for (int i = 0; i < CalculateFunction.validMisc.size(); i++){
            Character c = CalculateFunction.validMisc.get(i);
            this.addRenderableWidget(Button.builder(Component.literal(String.valueOf(c)), button -> add(c))
                    .bounds(this.width / 2 + (i * 10), this.height / 2 - 50, 10, 10)
                    .build());
        }
    }

    public void toggle(){
       DrawFunction.lock = !DrawFunction.lock;
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
       super.render(guiGraphics, mouseX, mouseY, partialTicks);
       if (this.minecraft != null) {
           StringBuilder sb = new StringBuilder();
           for (char c : DrawFunction.functions.get(this.minecraft.player)){
               sb.append(c);
           }
           guiGraphics.drawCenteredString(this.font, Component.literal(sb.toString()), this.width / 2, 10, 0xFFFFFF);
       }
    }

    public void increase(){
       DrawFunction.size++;
    }

    public void decrease(){
       if (DrawFunction.size >= 2){
           DrawFunction.size--;
       }
    }
    public void afast(){
            DrawFunction.distance++;
    }

    public void aproximate(){
       if (DrawFunction.distance >= 1){
           DrawFunction.distance--;
       }
    }

    public void add(char c){
       if (c == 'D') {
           delete();
           return;
       }
       if (this.minecraft != null) {
           List<Character> function = DrawFunction.functions.get(this.minecraft.player);
           function.add(c);
           DrawFunction.functions.put(this.minecraft.player, function);
       }
    }

    public void delete(){
        if (this.minecraft != null) {
            List<Character> function = DrawFunction.functions.get(this.minecraft.player);
            if (function.size() > 0) {
                function.remove(function.size() - 1);
                DrawFunction.functions.put(this.minecraft.player, function);
            }
        }
    }
}
