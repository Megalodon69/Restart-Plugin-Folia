package com.megalodon.restartplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class RestartPlugin extends JavaPlugin {

    private static RestartPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        
        // Save default config if it doesn't exist
        saveDefaultConfig();
        
        // Register the restart command
        RestartCommand restartCommand = new RestartCommand(this);
        getCommand("restart").setExecutor(restartCommand);
        
        getLogger().info("RestartPluginFolia has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RestartPluginFolia has been disabled!");
    }

    public static RestartPlugin getInstance() {
        return instance;
    }
}
