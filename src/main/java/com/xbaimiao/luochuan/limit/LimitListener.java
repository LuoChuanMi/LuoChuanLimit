package com.xbaimiao.luochuan.limit;

import com.xbaimiao.luochuan.limit.data.BlockBreakData;
import com.xbaimiao.luochuan.limit.data.PlayerData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import top.mcplugin.lib.common.subscribe.AutoListener;
import top.mcplugin.lib.module.item.ItemUtil;
import top.mcplugin.lib.module.lang.Lang;

@AutoListener
public class LimitListener implements Listener {


    @EventHandler(ignoreCancelled = true)
    public void b(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (ItemUtil.isAir(itemStack)) {
            return;
        }
        if (!checkItem(itemStack)) {
            return;
        }
        BlockBreakData blockBreakData = PlayerData.getBlockBreakData(player);
        if (blockBreakData.get(event.getBlock().getLocation()) > 64) {
            event.setCancelled(true);
            Lang.sendLang(player, "block-limit");
        } else {
            blockBreakData.add(event.getBlock().getLocation());
        }
    }

    private boolean checkItem(ItemStack itemStack) {
        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            if (enchantment.toString().contains("transfuse")) {
                return true;
            }
        }
        return false;
    }

}
