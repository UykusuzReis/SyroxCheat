package com.syrox;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class SyroxGui implements ModInitializer {
    private boolean isPressed = false;

    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Sag Shift (GLFW_KEY_RIGHT_SHIFT) Kontrolu
            boolean shiftPressed = GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            
            if (shiftPressed && !isPressed) {
                // Tüm özellikleri ekrana bas
                client.player.sendMessage(Text.literal("§b§l[Syrox 1.21.4] §fModüller Aktif!"), false);
                client.player.sendMessage(Text.literal("§6Combat: §7KillAura, BowAimbot, Hitboxes"), false);
                client.player.sendMessage(Text.literal("§9Movement: §7Flight, Scaffold, NoSlow, Velocity, NoFall"), false);
                client.player.sendMessage(Text.literal("§aPlayer: §7AutoClicker, Reach, AutoGap, AutoTool, SpeedMine"), false);
                client.player.sendMessage(Text.literal("§dVisual: §7Esp, Fullbright, Timer, Tracers"), false);
                isPressed = true;
            } else if (!shiftPressed) {
                isPressed = false;
            }
        });
    }
}
