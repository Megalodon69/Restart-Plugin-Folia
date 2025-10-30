# Configuration Examples

Here are some example configurations for different server needs:

## Quick Restart (5 second default)

```yaml
default-countdown: 5

countdown-intervals:
  - 5
  - 4
  - 3
  - 2
  - 1

min-countdown: 0
max-countdown: 60
```

## Extended Restart (2 minute default)

```yaml
default-countdown: 120

countdown-intervals:
  - 120
  - 60
  - 30
  - 15
  - 10
  - 5
  - 3
  - 2
  - 1

min-countdown: 10
max-countdown: 600
```

## Minimal Warnings

```yaml
default-countdown: 30

countdown-intervals:
  - 30
  - 10
  - 5
  - 1

min-countdown: 0
max-countdown: 300
```

## Custom Messages (Different Language Example)

```yaml
default-countdown: 10

messages:
  restart-initiated: "&c[Reinicio] &eEl servidor se reiniciará en &c{time} &esegundos!"
  countdown-warning: "&c[Reinicio] &eReiniciando en &c{time} &esegundos..."
  restart-now: "&c[Reinicio] &eEl servidor se está reiniciando ahora!"
  no-permission: "&c[Reinicio] &eNo tienes permiso para usar este comando!"
  invalid-time: "&c[Reinicio] &eTiempo inválido. Usa un número entre 0 y 300."

countdown-intervals:
  - 60
  - 30
  - 15
  - 10
  - 5
  - 3
  - 2
  - 1

min-countdown: 0
max-countdown: 300
```

## No-Countdown Mode (Always immediate restart)

```yaml
default-countdown: 0

messages:
  restart-initiated: "&c[Restart] &eServer is restarting immediately!"
  restart-now: "&c[Restart] &eServer is restarting now!"
  no-permission: "&c[Restart] &eYou don't have permission to use this command!"
  invalid-time: "&c[Restart] &eThis server is configured for immediate restarts only."

countdown-intervals: []

min-countdown: 0
max-countdown: 0
```

## Color Code Reference

Minecraft color codes that can be used in messages:

- `&0` - Black
- `&1` - Dark Blue
- `&2` - Dark Green
- `&3` - Dark Aqua
- `&4` - Dark Red
- `&5` - Dark Purple
- `&6` - Gold
- `&7` - Gray
- `&8` - Dark Gray
- `&9` - Blue
- `&a` - Green
- `&b` - Aqua
- `&c` - Red
- `&d` - Light Purple
- `&e` - Yellow
- `&f` - White
- `&l` - Bold
- `&m` - Strikethrough
- `&n` - Underline
- `&o` - Italic
- `&r` - Reset

Example: `&c&lATTENTION: &eServer restarting in &c{time} &eseconds!`
