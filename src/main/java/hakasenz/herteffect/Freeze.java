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


public class Freeze implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String FreezeParticleType;  // 声明粒子类型变量
    private String FreezePlayX;  // 声明 X 坐标变量
    private String FreezePlayY;  // 声明 Y 坐标变量
    private String FreezePlayZ;  // 声明 Z 坐标变量
    private String FreezeAmount;  // 声明粒子数量变量
    private String FreezeSpeed;  // 声明粒子速度变量

    public Freeze(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.FreezeParticleType = config.getString("FreezeParticleType", "ParticleType");
        this.FreezePlayX = config.getString("FreezePlayX", "0.0");
        this.FreezePlayY = config.getString("FreezePlayY", "0.2");
        this.FreezePlayZ = config.getString("FreezePlayZ", "0.0");
        this.FreezeAmount = config.getString("FreezeAmount", "10");
        this.FreezeSpeed = config.getString("FreezeSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Freezebutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material freezeParticleType;
                if (FreezeParticleType == null || Material.getMaterial(FreezeParticleType) == null) {
                    freezeParticleType = Material.REDSTONE_BLOCK;
                } else {
                        freezeParticleType = Material.getMaterial(FreezeParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(FreezeAmount), Double.parseDouble(FreezePlayX),
                        Double.parseDouble(FreezePlayY), Double.parseDouble(FreezePlayZ), Float.parseFloat(FreezeSpeed), freezeParticleType.createBlockData());
            }
        }
    }}