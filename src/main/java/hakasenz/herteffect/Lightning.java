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


public class Lightning implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String LightningParticleType;  // 声明粒子类型变量
    private String LightningPlayX;  // 声明 X 坐标变量
    private String LightningPlayY;  // 声明 Y 坐标变量
    private String LightningPlayZ;  // 声明 Z 坐标变量
    private String LightningAmount;  // 声明粒子数量变量
    private String LightningSpeed;  // 声明粒子速度变量

    public Lightning(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.LightningParticleType = config.getString("LightningParticleType", "ParticleType");
        this.LightningPlayX = config.getString("LightningPlayX", "0.0");
        this.LightningPlayY = config.getString("LightningPlayY", "0.2");
        this.LightningPlayZ = config.getString("LightningPlayZ", "0.0");
        this.LightningAmount = config.getString("LightningAmount", "10");
        this.LightningSpeed = config.getString("LightningSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Lightningbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material lightningParticleType;
                if (LightningParticleType == null || Material.getMaterial(LightningParticleType) == null) {
                    lightningParticleType = Material.REDSTONE_BLOCK;
                } else {
                    lightningParticleType = Material.getMaterial(LightningParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(LightningAmount), Double.parseDouble(LightningPlayX),
                        Double.parseDouble(LightningPlayY), Double.parseDouble(LightningPlayZ), Float.parseFloat(LightningSpeed), lightningParticleType.createBlockData());
            }
        }
    }}