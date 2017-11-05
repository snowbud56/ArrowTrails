package com.snowbud56;

import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class ArrowTrails extends JavaPlugin {

    private HashMap<UUID, EnumParticle> particles = new HashMap<>();
    private static ArrowTrails instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("trails").setExecutor(new TrailsCommand());
        getServer().getPluginManager().registerEvents(new ArrowShoot(), this);
        getLogger().info("Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void setParticles(UUID p, EnumParticle particle) {
        particles.put(p, particle);
    }

    public static ArrowTrails getInstance() {
        return instance;
    }

    public EnumParticle getParticle(UUID p) {
        if (!particles.containsKey(p)) return null;
        return particles.get(p);
    }
}
