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


public class hot_floor implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String hot_floorParticleType;  // 声明粒子类型变量
    private String hot_floorPlayX;  // 声明 X 坐标变量
    private String hot_floorPlayY;  // 声明 Y 坐标变量
    private String hot_floorPlayZ;  // 声明 Z 坐标变量
    private String hot_floorAmount;  // 声明粒子数量变量
    private String hot_floorSpeed;  // 声明粒子速度变量

    public hot_floor(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.hot_floorParticleType = config.getString("hot_floorParticleType", "ParticleType");
        this.hot_floorPlayX = config.getString("hot_floorPlayX", "0.0");
        this.hot_floorPlayY = config.getString("hot_floorPlayY", "0.2");
        this.hot_floorPlayZ = config.getString("hot_floorPlayZ", "0.0");
        this.hot_floorAmount = config.getString("hot_floorAmount", "10");
        this.hot_floorSpeed = config.getString("hot_floorSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("hot_floorbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material Hot_floorParticleType;
                if (hot_floorParticleType == null || Material.getMaterial(hot_floorParticleType) == null) {
                    Hot_floorParticleType = Material.REDSTONE_BLOCK;
                } else {
                    Hot_floorParticleType = Material.getMaterial(hot_floorParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(hot_floorAmount), Double.parseDouble(hot_floorPlayX),
                        Double.parseDouble(hot_floorPlayY), Double.parseDouble(hot_floorPlayZ), Float.parseFloat(hot_floorSpeed), Hot_floorParticleType.createBlockData());
            }
        }
    }}