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


public class Drowning implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String DrowningParticleType;  // 声明粒子类型变量
    private String DrowningPlayX;  // 声明 X 坐标变量
    private String DrowningPlayY;  // 声明 Y 坐标变量
    private String DrowningPlayZ;  // 声明 Z 坐标变量
    private String DrowningAmount;  // 声明粒子数量变量
    private String DrowningSpeed;  // 声明粒子速度变量

    public Drowning(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.DrowningParticleType = config.getString("DrowningParticleType", "ParticleType");
        this.DrowningPlayX = config.getString("DrowningPlayX", "0.0");
        this.DrowningPlayY = config.getString("DrowningPlayY", "0.2");
        this.DrowningPlayZ = config.getString("DrowningPlayZ", "0.0");
        this.DrowningAmount = config.getString("DrowningAmount", "10");
        this.DrowningSpeed = config.getString("DrowningSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Drowningbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material drowningParticleType;
                if (DrowningParticleType == null || Material.getMaterial(DrowningParticleType) == null) {
                    drowningParticleType = Material.REDSTONE_BLOCK;
                } else {
                    drowningParticleType = Material.getMaterial(DrowningParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(DrowningAmount), Double.parseDouble(DrowningPlayX),
                        Double.parseDouble(DrowningPlayY), Double.parseDouble(DrowningPlayZ), Float.parseFloat(DrowningSpeed), drowningParticleType.createBlockData());
            }
        }
    }}