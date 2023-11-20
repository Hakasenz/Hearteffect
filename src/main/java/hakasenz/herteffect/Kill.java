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


public class Kill implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String KillParticleType;  // 声明粒子类型变量
    private String KillPlayX;  // 声明 X 坐标变量
    private String KillPlayY;  // 声明 Y 坐标变量
    private String KillPlayZ;  // 声明 Z 坐标变量
    private String KillAmount;  // 声明粒子数量变量
    private String KillSpeed;  // 声明粒子速度变量

    public Kill(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.KillParticleType = config.getString("KillParticleType", "ParticleType");
        this.KillPlayX = config.getString("KillPlayX", "0.0");
        this.KillPlayY = config.getString("KillPlayY", "0.2");
        this.KillPlayZ = config.getString("KillPlayZ", "0.0");
        this.KillAmount = config.getString("KillAmount", "10");
        this.KillSpeed = config.getString("KillSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Killbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.KILL) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material killParticleType;
                if (KillParticleType == null || Material.getMaterial(KillParticleType) == null) {
                    killParticleType = Material.REDSTONE_BLOCK;
                } else {
                    killParticleType = Material.getMaterial(KillParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(KillAmount), Double.parseDouble(KillPlayX),
                        Double.parseDouble(KillPlayY), Double.parseDouble(KillPlayZ), Float.parseFloat(KillSpeed), killParticleType.createBlockData());
            }
        }
    }}