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


public class SonicBoom implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String SonicBoomParticleType;  // 声明粒子类型变量
    private String SonicBoomPlayX;  // 声明 X 坐标变量
    private String SonicBoomPlayY;  // 声明 Y 坐标变量
    private String SonicBoomPlayZ;  // 声明 Z 坐标变量
    private String SonicBoomAmount;  // 声明粒子数量变量
    private String SonicBoomSpeed;  // 声明粒子速度变量

    public SonicBoom(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.SonicBoomParticleType = config.getString("SonicBoomParticleType", "ParticleType");
        this.SonicBoomPlayX = config.getString("SonicBoomPlayX", "0.0");
        this.SonicBoomPlayY = config.getString("SonicBoomPlayY", "0.2");
        this.SonicBoomPlayZ = config.getString("SonicBoomPlayZ", "0.0");
        this.SonicBoomAmount = config.getString("SonicBoomAmount", "10");
        this.SonicBoomSpeed = config.getString("SonicBoomSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("SonicBoombutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.SONIC_BOOM) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material sonicBoomParticleType;
                if (SonicBoomParticleType == null || Material.getMaterial(SonicBoomParticleType) == null) {
                    sonicBoomParticleType = Material.REDSTONE_BLOCK;
                } else {
                    sonicBoomParticleType = Material.getMaterial(SonicBoomParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(SonicBoomAmount), Double.parseDouble(SonicBoomPlayX),
                        Double.parseDouble(SonicBoomPlayY), Double.parseDouble(SonicBoomPlayZ), Float.parseFloat(SonicBoomSpeed), sonicBoomParticleType.createBlockData());
            }
        }
    }}