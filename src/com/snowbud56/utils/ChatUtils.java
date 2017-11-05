package com.snowbud56.utils;

import net.md_5.bungee.api.ChatColor;

public class ChatUtils {
    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
