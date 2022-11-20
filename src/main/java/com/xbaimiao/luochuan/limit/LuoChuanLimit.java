package com.xbaimiao.luochuan.limit;

import top.mcplugin.lib.Plugin;

@SuppressWarnings("unused")
public class LuoChuanLimit extends Plugin {

    public LuoChuanLimit() {
        ignoreScan("shadow");
    }

    @Override
    public void enable() {
        saveDefaultConfig();
    }

    @Override
    public void disable() {
    }
}
