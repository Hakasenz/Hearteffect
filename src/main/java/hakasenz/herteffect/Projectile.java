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


public class Projectile implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String ProjectileParticleType;  // 声明粒子类型变量
    private String ProjectilePlayX;  // 声明 X 坐标变量
    private String ProjectilePlayY;  // 声明 Y 坐标变量
    private String ProjectilePlayZ;  // 声明 Z 坐标变量
    private String ProjectileAmount;  // 声明粒子数量变量
    private String ProjectileSpeed;  // 声明粒子速度变量

    public Projectile(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.ProjectileParticleType = config.getString("ProjectileParticleType", "ParticleType");
        this.ProjectilePlayX = config.getString("ProjectilePlayX", "0.0");
        this.ProjectilePlayY = config.getString("ProjectilePlayY", "0.2");
        this.ProjectilePlayZ = config.getString("ProjectilePlayZ", "0.0");
        this.ProjectileAmount = config.getString("ProjectileAmount", "10");
        this.ProjectileSpeed = config.getString("ProjectileSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Projectilebutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material projectileParticleType;
                if (ProjectileParticleType == null || Material.getMaterial(ProjectileParticleType) == null) {
                    projectileParticleType = Material.REDSTONE_BLOCK;
                } else {
                    projectileParticleType = Material.getMaterial(ProjectileParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(ProjectileAmount), Double.parseDouble(ProjectilePlayX),
                        Double.parseDouble(ProjectilePlayY), Double.parseDouble(ProjectilePlayZ), Float.parseFloat(ProjectileSpeed), projectileParticleType.createBlockData());
            }
        }
    }}