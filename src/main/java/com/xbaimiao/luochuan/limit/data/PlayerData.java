package com.xbaimiao.luochuan.limit.data;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerData {

    protected static HashMap<String, BlockBreakData> blockBreakDataHashMap = new HashMap<>();

    public static BlockBreakData getBlockBreakData(Player player) {
        blockBreakDataHashMap.putIfAbsent(player.getName(), new BlockBreakData());
        return blockBreakDataHashMap.get(player.getName());
    }

}
