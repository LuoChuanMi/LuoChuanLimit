package com.xbaimiao.luochuan.limit;

import com.xbaimiao.luochuan.limit.entity.ItemLimit;
import top.mcplugin.lib.Plugin;

@SuppressWarnings("unused")
public class LuoChuanLimit extends Plugin {

    private ItemLimit itemLimit;

    public LuoChuanLimit() {
        ignoreScan("shadow");
    }

    @Override
    public void enable() {
        saveDefaultConfig();
        itemLimit = new ItemLimit(getConfig().getLong("drop-item-available-time"));
        itemLimit.start();
    }

    @Override
    public void disable() {
        itemLimit.close();
    }
}
