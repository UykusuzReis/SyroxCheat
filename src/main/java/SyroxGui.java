package com.syrox;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.*;

public class SyroxGui implements ModInitializer {
    private boolean isPressed = false;
    private final Map<String, Boolean> modules = new HashMap<>();

    @Override
    public void onInitialize() {
        // --- TÜM ÖZELLİKLER ---
        String[] features = {
            "KillAura", "BowAimbot", "Hitboxes",      // Combat
            "AutoClicker", "Reach", "AutoGap",        // Player
            "Flight", "Scaffold", "NoSlow", "NoFall", // Movement
            "Esp", "Fullbright", "Timer"              // Visual/World
        };
        for (String f : features) modules.put(f, false);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Sağ Shift Kontrolü
            boolean shiftPressed = GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            
            if (shiftPressed && !isPressed) {
                client.player.sendMessage(Text.literal("§b§l[Syrox 1.21.4] §fÖzellikler Yüklendi!"), false);
                client.player.sendMessage(Text.literal("§9Combat: §7KillAura, Hitboxes, Aimbot"), false);
                client.player.sendMessage(Text.literal("§9Movement: §7Flight, Scaffold, NoSlow"), false);
                client.player.sendMessage(Text.literal("§9Player: §7AutoClicker, Reach, AutoGap"), false);
                client.player.sendMessage(Text.literal("§9Visual: §7Esp, Fullbright, Timer"), false);
                isPressed = true;
            } else if (!shiftPressed) {
                isPressed = false;
            }
        });
    }
}
