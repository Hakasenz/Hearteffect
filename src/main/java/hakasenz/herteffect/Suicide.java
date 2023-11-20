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


public class Suicide implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String SuicideParticleType;  // 声明粒子类型变量
    private String SuicidePlayX;  // 声明 X 坐标变量
    private String SuicidePlayY;  // 声明 Y 坐标变量
    private String SuicidePlayZ;  // 声明 Z 坐标变量
    private String SuicideAmount;  // 声明粒子数量变量
    private String SuicideSpeed;  // 声明粒子速度变量

    public Suicide(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.SuicideParticleType = config.getString("SuicideParticleType", "ParticleType");
        this.SuicidePlayX = config.getString("SuicidePlayX", "0.0");
        this.SuicidePlayY = config.getString("SuicidePlayY", "0.2");
        this.SuicidePlayZ = config.getString("SuicidePlayZ", "0.0");
        this.SuicideAmount = config.getString("SuicideAmount", "10");
        this.SuicideSpeed = config.getString("SuicideSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Suicidebutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.SUICIDE) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material suicideParticleType;
                if (SuicideParticleType == null || Material.getMaterial(SuicideParticleType) == null) {
                    suicideParticleType = Material.REDSTONE_BLOCK;
                } else {
                    suicideParticleType = Material.getMaterial(SuicideParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(SuicideAmount), Double.parseDouble(SuicidePlayX),
                        Double.parseDouble(SuicidePlayY), Double.parseDouble(SuicidePlayZ), Float.parseFloat(SuicideSpeed), suicideParticleType.createBlockData());
            }
        }
    }}