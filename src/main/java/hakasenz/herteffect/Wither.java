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


public class Wither implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String WitherParticleType;  // 声明粒子类型变量
    private String WitherPlayX;  // 声明 X 坐标变量
    private String WitherPlayY;  // 声明 Y 坐标变量
    private String WitherPlayZ;  // 声明 Z 坐标变量
    private String WitherAmount;  // 声明粒子数量变量
    private String WitherSpeed;  // 声明粒子速度变量

    public Wither(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.WitherParticleType = config.getString("WitherParticleType", "ParticleType");
        this.WitherPlayX = config.getString("WitherPlayX", "0.0");
        this.WitherPlayY = config.getString("WitherPlayY", "0.2");
        this.WitherPlayZ = config.getString("WitherPlayZ", "0.0");
        this.WitherAmount = config.getString("WitherAmount", "10");
        this.WitherSpeed = config.getString("WitherSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Witherbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.WITHER) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material witherParticleType;
                if (WitherParticleType == null || Material.getMaterial(WitherParticleType) == null) {
                    witherParticleType = Material.REDSTONE_BLOCK;
                } else {
                    witherParticleType = Material.getMaterial(WitherParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(WitherAmount), Double.parseDouble(WitherPlayX),
                        Double.parseDouble(WitherPlayY), Double.parseDouble(WitherPlayZ), Float.parseFloat(WitherSpeed), witherParticleType.createBlockData());
            }
        }
    }}
