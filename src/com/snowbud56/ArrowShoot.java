package com.snowbud56;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ArrowShoot implements Listener {

    @EventHandler
    public void ShootArrow(ProjectileLaunchEvent e) {
        if ((e.getEntity().getShooter() instanceof Player) && (e.getEntity() instanceof Arrow)) {
            Player p = (Player) e.getEntity().getShooter();
            if (ArrowTrails.getInstance().getParticle(p.getUniqueId()) == null) return;
            new BukkitRunnable(){
                Arrow a = (Arrow) e.getEntity();
                EnumParticle particle = ArrowTrails.getInstance().getParticle(p.getUniqueId());
                @Override
                public void run() {
                    if (a.isOnGround() || a.isDead() || a == null) cancel();
                    PacketPlayOutWorldParticles wp = new PacketPlayOutWorldParticles(particle, true, (float) a.getLocation().getX(), (float) a.getLocation().getY(), (float) a.getLocation().getZ(), 0F, 0F, 0F, 0, 5);
                    for (Player player : p.getWorld().getPlayers()) ((CraftPlayer) player).getHandle().playerConnection.sendPacket(wp);
                }
            }.runTaskTimer(ArrowTrails.getInstance(), 2, 1);
        }
    }

}
