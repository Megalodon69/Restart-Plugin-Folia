package com.megalodon.restartplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestartCommand implements CommandExecutor {

    private final RestartPlugin plugin;
    private BukkitTask countdownTask;
    private int remainingSeconds;
    private boolean restartInProgress = false;

    public RestartCommand(RestartPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check permission
        if (!sender.hasPermission("restartplugin.restart")) {
            String noPermMsg = plugin.getConfig().getString("messages.no-permission", "&cYou don't have permission!");
            sender.sendMessage(colorize(noPermMsg));
            return true;
        }

        // Parse countdown time
        int countdownTime = plugin.getConfig().getInt("default-countdown", 10);
        
        if (args.length > 0) {
            try {
                countdownTime = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                String invalidMsg = plugin.getConfig().getString("messages.invalid-time", "&cInvalid time specified!");
                sender.sendMessage(colorize(invalidMsg));
                return true;
            }
        }

        // Validate countdown time
        int minTime = plugin.getConfig().getInt("min-countdown", 0);
        int maxTime = plugin.getConfig().getInt("max-countdown", 300);
        
        if (countdownTime < minTime || countdownTime > maxTime) {
            String invalidMsg = plugin.getConfig().getString("messages.invalid-time", "&cInvalid time specified!");
            sender.sendMessage(colorize(invalidMsg));
            return true;
        }

        // Start the restart countdown
        startRestartCountdown(countdownTime);
        
        return true;
    }

    private void startRestartCountdown(int seconds) {
        if (restartInProgress) {
            return;
        }

        restartInProgress = true;
        remainingSeconds = seconds;

        // Broadcast initial message
        String initMsg = plugin.getConfig().getString("messages.restart-initiated", 
            "&c[Restart] Server will restart in &c{time} &eseconds!");
        broadcastMessage(initMsg.replace("{time}", String.valueOf(remainingSeconds)));

        // Get countdown intervals
        List<Integer> intervals = plugin.getConfig().getIntegerList("countdown-intervals");

        // If countdown is 0, restart immediately
        if (seconds == 0) {
            performRestart();
            return;
        }

        // Start countdown task using Folia-compatible global region scheduler
        countdownTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (remainingSeconds <= 0) {
                performRestart();
                return;
            }

            // Check if we should broadcast at this interval
            if (intervals.contains(remainingSeconds)) {
                String warningMsg = plugin.getConfig().getString("messages.countdown-warning",
                    "&c[Restart] Restarting in &c{time} &eseconds...");
                broadcastMessage(warningMsg.replace("{time}", String.valueOf(remainingSeconds)));
            }

            remainingSeconds--;
        }, 20L, 20L); // Run every second (20 ticks)
    }

    private void performRestart() {
        // Cancel the countdown task if running
        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
        }

        // Broadcast restart message
        String restartMsg = plugin.getConfig().getString("messages.restart-now",
            "&c[Restart] Server is restarting now!");
        broadcastMessage(restartMsg);

        // Kick all players
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(colorize("&c&lServer Restarting\n&eThe server is restarting now."));
        }

        // Schedule server shutdown/restart
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Bukkit.getServer().shutdown();
        }, 20L); // Wait 1 second before shutting down

        restartInProgress = false;
    }

    private void broadcastMessage(String message) {
        Bukkit.broadcastMessage(colorize(message));
        plugin.getLogger().info(ChatColor.stripColor(colorize(message)));
    }

    private String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
