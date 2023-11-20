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


public class DragonBreath implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String DragonBreathParticleType;  // 声明粒子类型变量
    private String DragonBreathPlayX;  // 声明 X 坐标变量
    private String DragonBreathPlayY;  // 声明 Y 坐标变量
    private String DragonBreathPlayZ;  // 声明 Z 坐标变量
    private String DragonBreathAmount;  // 声明粒子数量变量
    private String DragonBreathSpeed;  // 声明粒子速度变量

    public DragonBreath(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.DragonBreathParticleType = config.getString("DragonBreathParticleType", "ParticleType");
        this.DragonBreathPlayX = config.getString("DragonBreathPlayX", "0.0");
        this.DragonBreathPlayY = config.getString("DragonBreathPlayY", "0.2");
        this.DragonBreathPlayZ = config.getString("DragonBreathPlayZ", "0.0");
        this.DragonBreathAmount = config.getString("DragonBreathAmount", "10");
        this.DragonBreathSpeed = config.getString("DragonBreathSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("DragonBreathbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material dragonBreathParticleType;
                if (DragonBreathParticleType == null || Material.getMaterial(DragonBreathParticleType) == null) {
                    dragonBreathParticleType = Material.REDSTONE_BLOCK;
                } else {
                    dragonBreathParticleType = Material.getMaterial(DragonBreathParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(DragonBreathAmount), Double.parseDouble(DragonBreathPlayX),
                        Double.parseDouble(DragonBreathPlayY), Double.parseDouble(DragonBreathPlayZ), Float.parseFloat(DragonBreathSpeed), dragonBreathParticleType.createBlockData());
            }
        }
    }}