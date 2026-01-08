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

            // Sag Shift Kontrolu
            if (GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
                if (!isPressed) {
                    // Renkli Bildirim Mesajları
                    client.player.sendMessage(Text.literal("§b§l[Syrox Cheat 1.21.4] §fAktif!"), false);
                    client.player.sendMessage(Text.literal("§9Combat: §7KillAura, BowAimbot, Hitboxes"), false);
                    client.player.sendMessage(Text.literal("§aPlayer: §7AutoClicker, Reach, AutoGap, AutoTool"), false);
                    client.player.sendMessage(Text.literal("§eMovement: §7Flight, Scaffold, NoSlow, Velocity"), false);
                    client.player.sendMessage(Text.literal("§dVisual: §7Esp, Fullbright, Timer, Tracers"), false);
                    isPressed = true;
                }
            } else {
                isPressed = false;
            }
        });
    }
}
