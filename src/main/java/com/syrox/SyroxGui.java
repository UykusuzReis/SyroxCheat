package com.syrox;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.*;

public class SyroxGui implements ModInitializer {
    private boolean isPressed = false;
    
    // Modül Listesi
    private final List<String> combatModules = Arrays.asList("KillAura", "BowAimbot", "Hitboxes");
    private final List<String> movementModules = Arrays.asList("Flight", "Scaffold", "NoSlow", "NoFall", "Velocity");
    private final List<String> playerModules = Arrays.asList("AutoClicker", "Reach", "AutoGap", "AutoTool", "SpeedMine");
    private final List<String> visualModules = Arrays.asList("Esp", "Fullbright", "Tracer", "Timer", "Ambience");

    @Override
    public void onInitialize() {
        // Oyun her güncellendiğinde (tick) tuşu kontrol et
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // SAĞ SHIFT KONTROLÜ
            boolean shiftPressed = GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            
            if (shiftPressed && !isPressed) {
                // Menü Bildirimi
                client.player.sendMessage(Text.literal("§b§l[Syrox Cheat 1.21.4] §fÖzellikler Listeleniyor:"), false);
                
                // Kategorileri ve Özellikleri Yazdır
                sendModuleList(client, "§6Combat", combatModules);
                sendModuleList(client, "§9Movement", movementModules);
                sendModuleList(client, "§aPlayer", playerModules);
                sendModuleList(client, "§dVisual/World", visualModules);
                
                client.player.sendMessage(Text.literal("§e§oAyarlar: Range(3-6), FOV(1-360), Delay(1-100) Hazır!"), false);
                
                isPressed = true;
            } else if (!shiftPressed) {
                isPressed = false;
            }
        });
    }

    private void sendModuleList(net.minecraft.client.MinecraftClient client, String category, List<String> modules) {
        String list = String.join("§7, §f", modules);
        client.player.sendMessage(Text.literal(category + "§f: §f" + list), false);
    }
}
