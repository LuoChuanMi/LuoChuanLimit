package com.xbaimiao.luochuan.limit.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import top.mcplugin.lib.Plugin;

import java.util.HashMap;

public class BlockBreakData {

    protected final HashMap<String, Integer> data = new HashMap<>();

    public BlockBreakData() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Plugin.getPlugin(), data::clear, 20 * 600, 20 * 600);
    }

    public void add(Location location) {
        String loc = toString(location);
        if (data.containsKey(loc)) {
            data.put(loc, data.get(loc) + 1);
        } else {
            data.put(loc, 1);
        }
    }

    public int get(Location location) {
        data.putIfAbsent(toString(location), 0);
        return data.get(toString(location));
    }

    protected String toString(Location location) {
        return location.getWorld().getName() + "/" + location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ();
    }

}
