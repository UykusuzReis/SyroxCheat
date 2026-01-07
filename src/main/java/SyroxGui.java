import java.awt.Color;
import java.util.*;

public class SyroxGui {
    // Renkler
    int bgLightBlue = 0xFFADD8E6; // Açık Mavi
    int darkBlue = 0xFF00008B;    // Koyu Mavi
    int white = 0xFFFFFFFF;
    
    String currentTab = "Player";
    Map<String, Boolean> activeModules = new HashMap<>();

    public void render(int mouseX, int mouseY) {
        System.out.println("--- Syrox Cheat (betapvp) ---");
        
        // SOL PANEL: KATEGORİLER
        String[] tabs = {"Player", "Movement", "Combat", "World", "Visual"};
        for(String tab : tabs) {
            System.out.println("[" + tab + "] - Koyu Mavi Kutu");
        }

        // ÖZELLİKLER LİSTESİ
        if (currentTab.equals("Player")) {
            // Auto Clicker, Auto Tool, Auto Gap, Reach, Speed Mine, Auto Eat
            renderModule("Auto Clicker", true); // Sağ tık ayarlı
            renderModule("Auto Tool", false);
            renderModule("Reach", true); // Slider: 3 - 6
        } 
        else if (currentTab.equals("Combat")) {
            // Kill Aura, Bow Aimbot, Hitboxes
            renderModule("Kill Aura", true); // Weapon, Rotate, Fov (1-360), Range
        }
        else if (currentTab.equals("Movement")) {
            // Scaffold, Rocket, Flight, Long Jump, No Slow, Velocity, Safe Walk, No Fall
            renderModule("Flight", true); // Mode: Full, Jump, Ability
        }
        else if (currentTab.equals("World")) {
            // Ambience, Nuker, Timer
            renderModule("Timer", true); // Slider: 1 - 10
        }
        else if (currentTab.equals("Visual")) {
            // Fullbright, Esp, Tracer, Anti Blind, Nametags
            renderModule("Esp", false);
        }
    }

    private void renderModule(String name, boolean hasSettings) {
        boolean isActive = activeModules.getOrDefault(name, false);
        String dot = isActive ? "YEŞİL NOKTA" : "KIRMIZI NOKTA";
        System.out.println(name + " [" + dot + "]" + (hasSettings ? " (Sağ Tık: Ayarlar)" : ""));
    }

    public static void main(String[] args) {
        SyroxGui menu = new SyroxGui();
        menu.render(0, 0);
    }
}
