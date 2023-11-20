package hakasenz.herteffect;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;


public class WorldBorder implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String WorldBorderParticleType;  // 声明粒子类型变量
    private String WorldBorderPlayX;  // 声明 X 坐标变量
    private String WorldBorderPlayY;  // 声明 Y 坐标变量
    private String WorldBorderPlayZ;  // 声明 Z 坐标变量
    private String WorldBorderAmount;  // 声明粒子数量变量
    private String WorldBorderSpeed;  // 声明粒子速度变量

    public WorldBorder(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.WorldBorderParticleType = config.getString("WorldBorderParticleType", "ParticleType");
        this.WorldBorderPlayX = config.getString("WorldBorderPlayX", "0.0");
        this.WorldBorderPlayY = config.getString("WorldBorderPlayY", "0.2");
        this.WorldBorderPlayZ = config.getString("WorldBorderPlayZ", "0.0");
        this.WorldBorderAmount = config.getString("WorldBorderAmount", "10");
        this.WorldBorderSpeed = config.getString("WorldBorderSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("WorldBorderbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.WORLD_BORDER) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material worldBorderParticleType;
                if (WorldBorderParticleType == null || Material.getMaterial(WorldBorderParticleType) == null) {
                    worldBorderParticleType = Material.REDSTONE_BLOCK;
                } else {
                    worldBorderParticleType = Material.getMaterial(WorldBorderParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(WorldBorderAmount), Double.parseDouble(WorldBorderPlayX),
                        Double.parseDouble(WorldBorderPlayY), Double.parseDouble(WorldBorderPlayZ), Float.parseFloat(WorldBorderSpeed), worldBorderParticleType.createBlockData());
            }
        }
    }}