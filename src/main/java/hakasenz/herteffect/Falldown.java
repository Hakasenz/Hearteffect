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


public class Falldown implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String FalldownParticleType;  // 声明粒子类型变量
    private String FalldownPlayX;  // 声明 X 坐标变量
    private String FalldownPlayY;  // 声明 Y 坐标变量
    private String FalldownPlayZ;  // 声明 Z 坐标变量
    private String FalldownAmount;  // 声明粒子数量变量
    private String FalldownSpeed;  // 声明粒子速度变量

    public Falldown(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.FalldownParticleType = config.getString("FalldownParticleType", "ParticleType");
        this.FalldownPlayX = config.getString("FalldownPlayX", "0.0");
        this.FalldownPlayY = config.getString("FalldownPlayY", "0.2");
        this.FalldownPlayZ = config.getString("FalldownPlayZ", "0.0");
        this.FalldownAmount = config.getString("FalldownAmount", "10");
        this.FalldownSpeed = config.getString("FalldownSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Falldownbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material falldownParticleType;
                if (FalldownParticleType == null || Material.getMaterial(FalldownParticleType) == null) {
                    falldownParticleType = Material.REDSTONE_BLOCK;
                } else {
                    falldownParticleType = Material.getMaterial(FalldownParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(FalldownAmount), Double.parseDouble(FalldownPlayX),
                        Double.parseDouble(FalldownPlayY), Double.parseDouble(FalldownPlayZ), Float.parseFloat(FalldownSpeed), falldownParticleType.createBlockData());
            }
        }
    }}