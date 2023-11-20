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


public class Thorns implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String ThornsParticleType;  // 声明粒子类型变量
    private String ThornsPlayX;  // 声明 X 坐标变量
    private String ThornsPlayY;  // 声明 Y 坐标变量
    private String ThornsPlayZ;  // 声明 Z 坐标变量
    private String ThornsAmount;  // 声明粒子数量变量
    private String ThornsSpeed;  // 声明粒子速度变量

    public Thorns(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.ThornsParticleType = config.getString("ThornsParticleType", "ParticleType");
        this.ThornsPlayX = config.getString("ThornsPlayX", "0.0");
        this.ThornsPlayY = config.getString("ThornsPlayY", "0.2");
        this.ThornsPlayZ = config.getString("ThornsPlayZ", "0.0");
        this.ThornsAmount = config.getString("ThornsAmount", "10");
        this.ThornsSpeed = config.getString("ThornsSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Thornsbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.THORNS) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material thornsParticleType;
                if (ThornsParticleType == null || Material.getMaterial(ThornsParticleType) == null) {
                    thornsParticleType = Material.REDSTONE_BLOCK;
                } else {
                    thornsParticleType = Material.getMaterial(ThornsParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(ThornsAmount), Double.parseDouble(ThornsPlayX),
                        Double.parseDouble(ThornsPlayY), Double.parseDouble(ThornsPlayZ), Float.parseFloat(ThornsSpeed), thornsParticleType.createBlockData());
            }
        }
    }}