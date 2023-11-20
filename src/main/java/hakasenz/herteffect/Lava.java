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


public class Lava implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String LavaParticleType;  // 声明粒子类型变量
    private String LavaPlayX;  // 声明 X 坐标变量
    private String LavaPlayY;  // 声明 Y 坐标变量
    private String LavaPlayZ;  // 声明 Z 坐标变量
    private String LavaAmount;  // 声明粒子数量变量
    private String LavaSpeed;  // 声明粒子速度变量

    public Lava(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.LavaParticleType = config.getString("LavaParticleType", "ParticleType");
        this.LavaPlayX = config.getString("LavaPlayX", "0.0");
        this.LavaPlayY = config.getString("LavaPlayY", "0.2");
        this.LavaPlayZ = config.getString("LavaPlayZ", "0.0");
        this.LavaAmount = config.getString("LavaAmount", "10");
        this.LavaSpeed = config.getString("LavaSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Lavabutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material lavaParticleType;
                if (LavaParticleType == null || Material.getMaterial(LavaParticleType) == null) {
                    lavaParticleType = Material.REDSTONE_BLOCK;
                } else {
                    lavaParticleType = Material.getMaterial(LavaParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(LavaAmount), Double.parseDouble(LavaPlayX),
                        Double.parseDouble(LavaPlayY), Double.parseDouble(LavaPlayZ), Float.parseFloat(LavaSpeed), lavaParticleType.createBlockData());
            }
        }
    }}