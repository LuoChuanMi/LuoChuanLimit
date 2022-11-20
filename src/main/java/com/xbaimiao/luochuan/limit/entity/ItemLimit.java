package com.xbaimiao.luochuan.limit.entity;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import top.mcplugin.lib.module.item.ItemUtil;
import top.mcplugin.lib.util.Task;

import java.util.concurrent.CompletableFuture;

public class ItemLimit {

    private boolean close;
    private final long time;

    public ItemLimit(long time) {
        this.time = time * 20L;
    }

    public void start() {
        close = true;
        new Thread(() -> {
            while (close) {
                try {
                    clearItems().get();
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized CompletableFuture<Object> clearItems() {
        CompletableFuture<Object> completableFuture = new CompletableFuture<>();
        Task.sync(() -> {
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity instanceof Item item) {
                        if (!ItemUtil.hasLore(item.getItemStack()) && item.getTicksLived() > time) {
                            item.remove();
                        }
                    }
                }
            }
            completableFuture.complete(null);
        });
        return completableFuture;
    }

    public void close() {
        close = false;
    }

}
