package com.xbaimiao.luochuan.limit;

import com.xbaimiao.luochuan.limit.data.BlockBreakData;
import com.xbaimiao.luochuan.limit.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import top.mcplugin.lib.common.subscribe.AutoListener;
import top.mcplugin.lib.module.lang.Lang;

@AutoListener
public class LimitListener implements Listener {


    @EventHandler(ignoreCancelled = true)
    public void b(BlockBreakEvent event) {
        Player player = event.getPlayer();
        BlockBreakData blockBreakData = PlayerData.getBlockBreakData(player);
        if (blockBreakData.get(event.getBlock().getLocation()) > 100) {
            event.setCancelled(true);
            Lang.sendLang(player, "block-limit");
        } else {
            blockBreakData.add(event.getBlock().getLocation());
        }
    }

}
