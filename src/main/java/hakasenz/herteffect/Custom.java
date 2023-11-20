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


public class Custom implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String CustomParticleType;  // 声明粒子类型变量
    private String CustomPlayX;  // 声明 X 坐标变量
    private String CustomPlayY;  // 声明 Y 坐标变量
    private String CustomPlayZ;  // 声明 Z 坐标变量
    private String CustomAmount;  // 声明粒子数量变量
    private String CustomSpeed;  // 声明粒子速度变量

    public Custom(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.CustomParticleType = config.getString("CustomParticleType", "ParticleType");
        this.CustomPlayX = config.getString("CustomPlayX", "0.0");
        this.CustomPlayY = config.getString("CustomPlayY", "0.2");
        this.CustomPlayZ = config.getString("CustomPlayZ", "0.0");
        this.CustomAmount = config.getString("CustomAmount", "10");
        this.CustomSpeed = config.getString("CustomSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Custombutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.CUSTOM) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material customParticleType;
                if (CustomParticleType == null || Material.getMaterial(CustomParticleType) == null) {
                    customParticleType = Material.REDSTONE_BLOCK;
                } else {
                    customParticleType = Material.getMaterial(CustomParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(CustomAmount), Double.parseDouble(CustomPlayX),
                        Double.parseDouble(CustomPlayY), Double.parseDouble(CustomPlayZ), Float.parseFloat(CustomSpeed), customParticleType.createBlockData());
            }
        }
    }}