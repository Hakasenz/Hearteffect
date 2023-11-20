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


public class Suffocation implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String SuffocationParticleType;  // 声明粒子类型变量
    private String SuffocationPlayX;  // 声明 X 坐标变量
    private String SuffocationPlayY;  // 声明 Y 坐标变量
    private String SuffocationPlayZ;  // 声明 Z 坐标变量
    private String SuffocationAmount;  // 声明粒子数量变量
    private String SuffocationSpeed;  // 声明粒子速度变量

    public Suffocation(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.SuffocationParticleType = config.getString("SuffocationParticleType", "ParticleType");
        this.SuffocationPlayX = config.getString("SuffocationPlayX", "0.0");
        this.SuffocationPlayY = config.getString("SuffocationPlayY", "0.2");
        this.SuffocationPlayZ = config.getString("SuffocationPlayZ", "0.0");
        this.SuffocationAmount = config.getString("SuffocationAmount", "10");
        this.SuffocationSpeed = config.getString("SuffocationSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Suffocationbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material suffocationParticleType;
                if (SuffocationParticleType == null || Material.getMaterial(SuffocationParticleType) == null) {
                    suffocationParticleType = Material.REDSTONE_BLOCK;
                } else {
                    suffocationParticleType = Material.getMaterial(SuffocationParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(SuffocationAmount), Double.parseDouble(SuffocationPlayX),
                        Double.parseDouble(SuffocationPlayY), Double.parseDouble(SuffocationPlayZ), Float.parseFloat(SuffocationSpeed), suffocationParticleType.createBlockData());
            }
        }
    }}