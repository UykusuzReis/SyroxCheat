package com.syrox;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.*;

public class SyroxGui implements ModInitializer {
    private boolean isPressed = false;
    private String currentTab = "Player";
    
    // Tüm Özelliklerin Listesi
    private final Map<String, Boolean> modules = new HashMap<>();
    private final Map<String, Double> settings = new HashMap<>();

    @Override
    public void onInitialize() {
        setupFeatures();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            boolean shiftPressed = GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            
            if (shiftPressed && !isPressed) {
                // Menü her açıldığında mevcut özellikleri mesaj olarak gösterir
                client.player.sendMessage(Text.literal("§b[Syrox Cheat] §fAçılıyor..."), false);
                client.player.sendMessage(Text.literal("§7Sekmeler: Player, Movement, Combat, World, Visual"), false);
                isPressed = true;
            } else if (!shiftPressed) {
                isPressed = false;
            }
        });
    }

    private void setupFeatures() {
        // PLAYER: Auto Clicker, Auto Tool, Auto Gap, Reach, Speed Mine, Auto Eat
        // COMBAT: Kill Aura (Weapon, Rotate, Fov 1-360, Hit Chance, Range 3-6)
        // MOVEMENT: Scaffold (Delay 1-10), Flight (Mode), Long Jump, No Slow
        // WORLD: Ambience, Timer (Speed 1-10)
        // VISUAL: Fullbright, Esp, Tracer
        
        String[] all = {"AutoClicker", "KillAura", "Flight", "Scaffold", "Timer", "Esp"};
        for(String s : all) modules.put(s, false);
        
        settings.put("Fov", 90.0);
        settings.put("Reach", 3.0);
        settings.put("TimerSpeed", 1.0);
    }
}
