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

public class FlyIntoWall implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String FlyIntoWallParticleType;  // 声明粒子类型变量
    private String FlyIntoWallPlayX;  // 声明 X 坐标变量
    private String FlyIntoWallPlayY;  // 声明 Y 坐标变量
    private String FlyIntoWallPlayZ;  // 声明 Z 坐标变量
    private String FlyIntoWallAmount;  // 声明粒子数量变量
    private String FlyIntoWallSpeed;  // 声明粒子速度变量

    public FlyIntoWall(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.FlyIntoWallParticleType = config.getString("FlyIntoWallParticleType", "ParticleType");
        this.FlyIntoWallPlayX = config.getString("FlyIntoWallPlayX", "0.0");
        this.FlyIntoWallPlayY = config.getString("FlyIntoWallPlayY", "0.2");
        this.FlyIntoWallPlayZ = config.getString("FlyIntoWallPlayZ", "0.0");
        this.FlyIntoWallAmount = config.getString("FlyIntoWallAmount", "10");
        this.FlyIntoWallSpeed = config.getString("FlyIntoWallSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("FlyIntoWallbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material flyIntoWallParticleType;
                if (FlyIntoWallParticleType == null || Material.getMaterial(FlyIntoWallParticleType) == null) {
                    flyIntoWallParticleType = Material.REDSTONE_BLOCK;
                } else {
                    flyIntoWallParticleType = Material.getMaterial(FlyIntoWallParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(FlyIntoWallAmount), Double.parseDouble(FlyIntoWallPlayX),
                        Double.parseDouble(FlyIntoWallPlayY), Double.parseDouble(FlyIntoWallPlayZ), Float.parseFloat(FlyIntoWallSpeed), flyIntoWallParticleType.createBlockData());
            }
        }
    }}