# Restart-Plugin-Folia

A Minecraft Folia server plugin that provides a `/restart` command to restart the server with a customizable countdown timer.

## Features

- **Permission-based access**: Only players with the `restartplugin.restart` permission can execute the restart command
- **Customizable countdown**: Configure default countdown time and intervals
- **Flexible countdown override**: Specify custom countdown time via command arguments
- **Broadcast messages**: Notifies all players during the countdown
- **Colored messages**: Full support for Minecraft color codes
- **Folia compatible**: Works with both Folia and Paper/Spigot servers

## Commands

- `/restart [seconds]` - Restarts the server with an optional countdown time
  - Without arguments: Uses the default countdown time from config
  - With argument: Uses the specified countdown time (must be between min and max values)

## Permissions

- `restartplugin.restart` - Allows player to use the restart command (default: op)

## Configuration

Edit `plugins/RestartPluginFolia/config.yml` to customize the plugin:

```yaml
# Default countdown time in seconds before server restart
default-countdown: 10

# Messages (Use {time} placeholder for countdown time)
messages:
  restart-initiated: "&c[Restart] &eServer will restart in &c{time} &eseconds!"
  countdown-warning: "&c[Restart] &eRestarting in &c{time} &eseconds..."
  restart-now: "&c[Restart] &eServer is restarting now!"
  no-permission: "&c[Restart] &eYou don't have permission to use this command!"
  invalid-time: "&c[Restart] &eInvalid time specified. Please use a number between 0 and 300."

# Countdown intervals (when to show warnings during countdown)
countdown-intervals:
  - 60
  - 30
  - 15
  - 10
  - 5
  - 4
  - 3
  - 2
  - 1

# Minimum and maximum countdown time allowed
min-countdown: 0
max-countdown: 300
```

## Building

This plugin uses Maven for building. To compile:

```bash
mvn clean package
```

The compiled JAR will be in the `target/` directory as `RestartPluginFolia-1.0.0.jar`.

## Installation

1. Download the plugin JAR file
2. Place it in your server's `plugins/` directory
3. Restart your server
4. Edit the config file at `plugins/RestartPluginFolia/config.yml` if needed
5. Use `/reload` or restart the server again to apply config changes

## Usage Examples

```
/restart           # Restart with default countdown (10 seconds)
/restart 30        # Restart with 30 second countdown
/restart 0         # Restart immediately (no countdown)
```

## Requirements

- Java 17 or higher
- Minecraft server: Spigot/Paper/Folia 1.20.1 or compatible versions

## License

This plugin is open source. Feel free to use and modify as needed.

