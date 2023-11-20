package hakasenz.herteffect;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;


public class Cramming implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String CrammingParticleType;  // 声明粒子类型变量
    private String CrammingPlayX;  // 声明 X 坐标变量
    private String CrammingPlayY;  // 声明 Y 坐标变量
    private String CrammingPlayZ;  // 声明 Z 坐标变量
    private String CrammingAmount;  // 声明粒子数量变量
    private String CrammingSpeed;  // 声明粒子速度变量

    public Cramming(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.CrammingParticleType = config.getString("CrammingParticleType", "ParticleType");
        this.CrammingPlayX = config.getString("CrammingPlayX", "0.0");
        this.CrammingPlayY = config.getString("CrammingPlayY", "0.2");
        this.CrammingPlayZ = config.getString("CrammingPlayZ", "0.0");
        this.CrammingAmount = config.getString("CrammingAmount", "10");
        this.CrammingSpeed = config.getString("CrammingSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Crammingbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.CRAMMING) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material crammingParticleType;
                if (CrammingParticleType == null || Material.getMaterial(CrammingParticleType) == null) {
                    crammingParticleType = Material.REDSTONE_BLOCK;
                } else {
                    crammingParticleType = Material.getMaterial(CrammingParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(CrammingAmount), Double.parseDouble(CrammingPlayX),
                        Double.parseDouble(CrammingPlayY), Double.parseDouble(CrammingPlayZ), Float.parseFloat(CrammingSpeed), crammingParticleType.createBlockData());
            }
        }
    }}