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

public class Void implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String VoidParticleType;  // 声明粒子类型变量
    private String VoidPlayX;  // 声明 X 坐标变量
    private String VoidPlayY;  // 声明 Y 坐标变量
    private String VoidPlayZ;  // 声明 Z 坐标变量
    private String VoidAmount;  // 声明粒子数量变量
    private String VoidSpeed;  // 声明粒子速度变量

    public Void(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.VoidParticleType = config.getString("VoidParticleType", "ParticleType");
        this.VoidPlayX = config.getString("VoidPlayX", "0.0");
        this.VoidPlayY = config.getString("VoidPlayY", "0.2");
        this.VoidPlayZ = config.getString("VoidPlayZ", "0.0");
        this.VoidAmount = config.getString("VoidAmount", "10");
        this.VoidSpeed = config.getString("VoidSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Voidbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material voidParticleType;
                if (VoidParticleType == null || Material.getMaterial(VoidParticleType) == null) {
                    voidParticleType = Material.REDSTONE_BLOCK;
                } else {
                    voidParticleType = Material.getMaterial(VoidParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(VoidAmount), Double.parseDouble(VoidPlayX),
                        Double.parseDouble(VoidPlayY), Double.parseDouble(VoidPlayZ), Float.parseFloat(VoidSpeed), voidParticleType.createBlockData());
            }
        }
    }}