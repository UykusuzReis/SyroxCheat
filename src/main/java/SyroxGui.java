package com.syrox;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.*;

public class SyroxGui implements ModInitializer {
    // Menü Durumu ve Ayarlar
    private boolean isMenuOpen = false;
    private String currentTab = "Player";
    
    // Özellik Listeleri (Hafızada tutmak için)
    private final Map<String, Boolean> modules = new HashMap<>();
    private final Map<String, Double> settings = new HashMap<>();

    @Override
    public void onInitialize() {
        // Modülleri Varsayılan Olarak Kapalı Başlat
        setupModules();

        // SAĞ SHIFT TAKİBİ
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // GLFW_KEY_RIGHT_SHIFT = Sağ Shift
            if (GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
                if (!isMenuOpen) {
                    renderSyroxMenu(client);
                    isMenuOpen = true;
                }
            } else {
                isMenuOpen = false;
            }
        });
    }

    private void setupModules() {
        // PLAYER
        String[] p = {"Auto Clicker", "Auto Tool", "Auto Gap", "Reach", "Speed Mine", "Auto Eat"};
        // MOVEMENT
        String[] m = {"Scaffold", "Rocket", "Flight", "Long Jump", "No Slow", "Velocity", "Safe Walk", "No Fall"};
        // COMBAT
        String[] c = {"Kill Aura", "Bow Aimbot", "Hitboxes"};
        // WORLD & VISUAL
        String[] wv = {"Ambience", "Nuker", "Timer", "Fullbright", "Esp", "Tracer"};

        for(String s : p) modules.put(s, false);
        for(String s : m) modules.put(s, false);
        for(String s : c) modules.put(s, false);
        for(String s : wv) modules.put(s, false);

        // Örnek Ayarlar (Sliderlar için)
        settings.put("Reach_Value", 3.0);
        settings.put("Fov_Value", 90.0);
        settings.put("Timer_Speed", 1.0);
    }

    private void renderSyroxMenu(MinecraftClient client) {
        // Oyuna mesaj göndererek menünün aktif olduğunu bildir
        client.player.sendMessage(Text.literal("§b[Syrox] §fMenü Sekmesi: §9" + currentTab), true);
        
        // Konsola tüm detayları yazdır (Görsel GUI ekranı tam oluşana kadar buradan takip edilir)
        System.out.println("--- Syrox Cheat (betapvp) ---");
        System.out.println("Tab: " + currentTab);
        System.out.println("Renkler: Arkaplan Açık Mavi (#ADD8E6), Kutular Koyu Mavi (#00008B)");
    }
}
