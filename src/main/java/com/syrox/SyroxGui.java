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
            if (GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
                if (!isPressed) {
                    client.player.sendMessage(Text.literal("§b[Syrox 1.21.4] §aTüm Modüller Hazır!"), false);
                    client.player.sendMessage(Text.literal("§fCombat: §7KillAura, BowAimbot"), false);
                    client.player.sendMessage(Text.literal("§fPlayer: §7AutoClicker, Reach, AutoGap"), false);
                    client.player.sendMessage(Text.literal("§fMovement: §7Flight, Scaffold, NoSlow"), false);
                    client.player.sendMessage(Text.literal("§fVisual: §7Esp, Fullbright, Timer"), false);
                    isPressed = true;
                }
            } else { isPressed = false; }
        });
    }
}
