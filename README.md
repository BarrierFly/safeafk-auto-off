# SafeAFK Auto Off / 安全挂机自动关闭

A companion mod for [TweakerMore](https://github.com/Fallen-Breath/tweakermore) that automatically disables the safe AFK feature when it triggers, and reminds you on your next login.

[TweakerMore](https://github.com/Fallen-Breath/tweakermore) 的扩展 mod。当 TweakerMore 的安全挂机功能触发自动断开时，自动关闭安全挂机，并在下次进入游戏时发送提示。

## How It Works / 工作原理

1. Enable TweakerMore's **safeAfk** feature / 启用 TweakerMore 的安全挂机功能
2. Take damage, health drops below threshold / 受到伤害，血量低于阈值
3. TweakerMore disconnects you / TweakerMore 自动断开连接
4. **This mod turns off safeAfk** and force-saves the config / **本 mod 自动关闭安全挂机**并立即保存配置
5. Next time you join a world/server, a **yellow reminder** appears in chat / 下次进入任意世界/服务器时，聊天栏出现**黄色提示**：

   > Safe AFK was automatically disabled because you took damage in your previous session.

## Why? / 为什么需要？

When safe AFK triggers and disconnects you, TweakerMore leaves the feature enabled. If you reconnect, you risk getting disconnected again by another hit. This mod ensures safe AFK is off after it triggers once, so you can safely rejoin.

安全挂机触发断开后，TweakerMore 不会自动关闭该功能。如果重连回去继续挂机，可能再次被打断。本 mod 确保触发一次后自动关闭，避免反复断开。

## Installation / 安装

1. Install [Fabric Loader](https://fabricmc.net/use/) / 安装 Fabric
2. Install [malilib](https://masa.dy.fi/mcmods/malilib/) / 安装 malilib
3. Install [TweakerMore](https://github.com/Fallen-Breath/tweakermore) / 安装 TweakerMore
4. Download the jar from [Releases](https://github.com/BarrierFly/safeafk-auto-off/releases) / 从 Releases 下载对应版本的 jar
5. Place it in `.minecraft/mods/` / 放入 `.minecraft/mods/` 文件夹

## Supported Versions / 支持的版本

| Minecraft | Jar |
|---|---|
| 1.17.1 | `safeafk-auto-off-v1.0.0-mc1.17.1.jar` |
| 1.19.4 | `safeafk-auto-off-v1.0.0-mc1.19.4.jar` |
| 1.20.1 | `safeafk-auto-off-v1.0.0-mc1.20.1.jar` |
| 1.20.6 | `safeafk-auto-off-v1.0.0-mc1.20.6.jar` |
| 1.21.1 | `safeafk-auto-off-v1.0.0-mc1.21.1.jar` |
| 1.21.11 | `safeafk-auto-off-v1.0.0-mc1.21.11.jar` |

## Building from Source / 从源码构建

### Prerequisites / 环境要求

- JDK 21+

### Steps / 步骤

1. Clone this repo / 克隆本仓库：

   ```bash
   git clone git@github.com:BarrierFly/safeafk-auto-off.git
   cd safeafk-auto-off
   ```

2. Clone and build TweakerMore **first** / **先**克隆并构建 TweakerMore：

   ```bash
   cd ..
   git clone https://github.com/Fallen-Breath/tweakermore.git
   cd tweakermore
   ./gradlew buildAndGather --configure-on-demand
   cd ../safeafk-auto-off
   ```

3. Build all versions / 构建全部版本：

   ```bash
   ./gradlew buildAndGather --configure-on-demand
   ```

4. Find the jars in `build/libs/` / 在 `build/libs/` 中找到构建产物。

## License / 许可证

LGPL-3.0 — same as TweakerMore / 与 TweakerMore 一致。
