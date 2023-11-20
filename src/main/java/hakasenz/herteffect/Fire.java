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


public class Fire implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String FireParticleType;  // 声明粒子类型变量
    private String FirePlayX;  // 声明 X 坐标变量
    private String FirePlayY;  // 声明 Y 坐标变量
    private String FirePlayZ;  // 声明 Z 坐标变量
    private String FireAmount;  // 声明粒子数量变量
    private String FireSpeed;  // 声明粒子速度变量

    public Fire(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.FireParticleType = config.getString("FireParticleType", "ParticleType");
        this.FirePlayX = config.getString("FirePlayX", "0.0");
        this.FirePlayY = config.getString("FirePlayY", "0.2");
        this.FirePlayZ = config.getString("FirePlayZ", "0.0");
        this.FireAmount = config.getString("FireAmount", "10");
        this.FireSpeed = config.getString("FireSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Firebutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FIRE) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material fireParticleType;
                if (FireParticleType == null || Material.getMaterial(FireParticleType) == null) {
                    fireParticleType = Material.REDSTONE_BLOCK;
                } else {
                    fireParticleType = Material.getMaterial(FireParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(FireAmount), Double.parseDouble(FirePlayX),
                        Double.parseDouble(FirePlayY), Double.parseDouble(FirePlayZ), Float.parseFloat(FireSpeed), fireParticleType.createBlockData());
            }
        }
    }}