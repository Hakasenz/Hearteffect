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


public class Starvation implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String StarvationParticleType;  // 声明粒子类型变量
    private String StarvationPlayX;  // 声明 X 坐标变量
    private String StarvationPlayY;  // 声明 Y 坐标变量
    private String StarvationPlayZ;  // 声明 Z 坐标变量
    private String StarvationAmount;  // 声明粒子数量变量
    private String StarvationSpeed;  // 声明粒子速度变量

    public Starvation(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.StarvationParticleType = config.getString("StarvationParticleType", "ParticleType");
        this.StarvationPlayX = config.getString("StarvationPlayX", "0.0");
        this.StarvationPlayY = config.getString("StarvationPlayY", "0.2");
        this.StarvationPlayZ = config.getString("StarvationPlayZ", "0.0");
        this.StarvationAmount = config.getString("StarvationAmount", "10");
        this.StarvationSpeed = config.getString("StarvationSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Starvationbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material starvationParticleType;
                if (StarvationParticleType == null || Material.getMaterial(StarvationParticleType) == null) {
                    starvationParticleType = Material.REDSTONE_BLOCK;
                } else {
                    starvationParticleType = Material.getMaterial(StarvationParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(StarvationAmount), Double.parseDouble(StarvationPlayX),
                        Double.parseDouble(StarvationPlayY), Double.parseDouble(StarvationPlayZ), Float.parseFloat(StarvationSpeed), starvationParticleType.createBlockData());
            }
        }
    }}