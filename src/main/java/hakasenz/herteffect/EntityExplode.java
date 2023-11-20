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


public class EntityExplode implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String EntityExplodeParticleType;  // 声明粒子类型变量
    private String EntityExplodePlayX;  // 声明 X 坐标变量
    private String EntityExplodePlayY;  // 声明 Y 坐标变量
    private String EntityExplodePlayZ;  // 声明 Z 坐标变量
    private String EntityExplodeAmount;  // 声明粒子数量变量
    private String EntityExplodeSpeed;  // 声明粒子速度变量

    public EntityExplode(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.EntityExplodeParticleType = config.getString("EntityExplodeParticleType", "ParticleType");
        this.EntityExplodePlayX = config.getString("EntityExplodePlayX", "0.0");
        this.EntityExplodePlayY = config.getString("EntityExplodePlayY", "0.2");
        this.EntityExplodePlayZ = config.getString("EntityExplodePlayZ", "0.0");
        this.EntityExplodeAmount = config.getString("EntityExplodeAmount", "10");
        this.EntityExplodeSpeed = config.getString("EntityExplodeSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("EntityExplodebutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material entityExplodeParticleType;
                if (EntityExplodeParticleType == null || Material.getMaterial(EntityExplodeParticleType) == null) {
                    entityExplodeParticleType = Material.REDSTONE_BLOCK;
                } else {
                    entityExplodeParticleType = Material.getMaterial(EntityExplodeParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(EntityExplodeAmount), Double.parseDouble(EntityExplodePlayX),
                        Double.parseDouble(EntityExplodePlayY), Double.parseDouble(EntityExplodePlayZ), Float.parseFloat(EntityExplodeSpeed), entityExplodeParticleType.createBlockData());
            }
        }
    }}