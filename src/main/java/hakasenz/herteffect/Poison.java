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


public class Poison implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String PoisonParticleType;  // 声明粒子类型变量
    private String PoisonPlayX;  // 声明 X 坐标变量
    private String PoisonPlayY;  // 声明 Y 坐标变量
    private String PoisonPlayZ;  // 声明 Z 坐标变量
    private String PoisonAmount;  // 声明粒子数量变量
    private String PoisonSpeed;  // 声明粒子速度变量

    public Poison(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.PoisonParticleType = config.getString("PoisonParticleType", "ParticleType");
        this.PoisonPlayX = config.getString("PoisonPlayX", "0.0");
        this.PoisonPlayY = config.getString("PoisonPlayY", "0.2");
        this.PoisonPlayZ = config.getString("PoisonPlayZ", "0.0");
        this.PoisonAmount = config.getString("PoisonAmount", "10");
        this.PoisonSpeed = config.getString("PoisonSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Poisonbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.POISON) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material poisonParticleType;
                if (PoisonParticleType == null || Material.getMaterial(PoisonParticleType) == null) {
                    poisonParticleType = Material.REDSTONE_BLOCK;
                } else {
                    poisonParticleType = Material.getMaterial(PoisonParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(PoisonAmount), Double.parseDouble(PoisonPlayX),
                        Double.parseDouble(PoisonPlayY), Double.parseDouble(PoisonPlayZ), Float.parseFloat(PoisonSpeed), poisonParticleType.createBlockData());
            }
        }
    }}

