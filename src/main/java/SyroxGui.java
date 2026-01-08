package com.syrox;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.*;

public class SyroxGui implements ModInitializer {
    private boolean isPressed = false;
    
    // Modül ve Ayar Saklayıcıları
    private final Map<String, Boolean> modules = new HashMap<>();
    private final Map<String, Double> settings = new HashMap<>();

    @Override
    public void onInitialize() {
        initializeAllFeatures();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Sağ Shift (GLFW_KEY_RIGHT_SHIFT) Kontrolü
            boolean shiftPressed = GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            
            if (shiftPressed && !isPressed) {
                // Oyuncu mesajı ve renkli bildirimler
                client.player.sendMessage(Text.literal("§b§l[Syrox Cheat 1.21.4] §fMenü Sistemleri Aktif!"), false);
                sendCategoryInfo(client);
                isPressed = true;
            } else if (!shiftPressed) {
                isPressed = false;
            }
        });
    }

    private void initializeAllFeatures() {
        // COMBAT: KillAura, BowAimbot, Hitboxes (Range: 3.0-6.0, FOV: 1-360)
        modules.put("KillAura", false);
        modules.put("BowAimbot", false);
        modules.put("Hitboxes", false);

        // PLAYER: AutoClicker, AutoTool, AutoGap, Reach, SpeedMine, AutoEat
        modules.put("AutoClicker", false);
        modules.put("Reach", false);
        settings.put("ReachValue", 3.0);

        // MOVEMENT: Scaffold, Flight, LongJump, NoSlow, Velocity, SafeWalk, NoFall
        modules.put("Flight", false);
        modules.put("Scaffold", false);
        modules.put("NoSlow", false);

        // WORLD & VISUAL: Timer, Ambience, Nuker, Fullbright, Esp, Tracer
        modules.put("Timer", false);
        modules.put("Fullbright", true);
        modules.put("Esp", false);
        settings.put("TimerSpeed", 1.0);
    }

    private void sendCategoryInfo(net.minecraft.client.MinecraftClient client) {
        client.player.sendMessage(Text.literal("§9> Player: §7AutoClicker, Reach, AutoGap..."), false);
        client.player.sendMessage(Text.literal("§9> Combat: §7KillAura, Hitboxes..."), false);
        client.player.sendMessage(Text.literal("§9> Movement: §7Flight, Scaffold, NoSlow..."), false);
        client.player.sendMessage(Text.literal("§9> Visual: §7Esp, Fullbright, Tracers..."), false);
    }
}
