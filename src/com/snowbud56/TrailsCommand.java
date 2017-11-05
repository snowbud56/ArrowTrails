package com.snowbud56;

import com.snowbud56.utils.ChatUtils;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrailsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (args.length == 0) {
            String text = "";
            for (EnumParticle particle : EnumParticle.values()) {
                text += particle.name() + ", ";
            }
            text += "OFF";
            p.sendMessage(ChatUtils.format("&7/trails <particle>"));
            p.sendMessage(ChatUtils.format("&7" + text));
        } else {
            if (args[0].equalsIgnoreCase("off")) {
                ArrowTrails.getInstance().setParticles(p.getUniqueId(), null);
                p.sendMessage(ChatUtils.format("&cParticles reset"));
                return false;
            }
            try {
                EnumParticle particle = EnumParticle.valueOf(args[0].toUpperCase());
                ArrowTrails.getInstance().setParticles(p.getUniqueId(), particle);
                p.sendMessage(ChatUtils.format("&aParticle set!"));
            } catch (Exception e) {
                p.sendMessage(ChatUtils.format("&cInvalid particle: " + args[0]));
            }
        }
        return false;
    }
}
