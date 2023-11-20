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


public class Melting implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String MeltingParticleType;  // 声明粒子类型变量
    private String MeltingPlayX;  // 声明 X 坐标变量
    private String MeltingPlayY;  // 声明 Y 坐标变量
    private String MeltingPlayZ;  // 声明 Z 坐标变量
    private String MeltingAmount;  // 声明粒子数量变量
    private String MeltingSpeed;  // 声明粒子速度变量

    public Melting(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.MeltingParticleType = config.getString("MeltingParticleType", "ParticleType");
        this.MeltingPlayX = config.getString("MeltingPlayX", "0.0");
        this.MeltingPlayY = config.getString("MeltingPlayY", "0.2");
        this.MeltingPlayZ = config.getString("MeltingPlayZ", "0.0");
        this.MeltingAmount = config.getString("MeltingAmount", "10");
        this.MeltingSpeed = config.getString("MeltingSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Meltingbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.MELTING) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material meltingParticleType;
                if (MeltingParticleType == null || Material.getMaterial(MeltingParticleType) == null) {
                    meltingParticleType = Material.REDSTONE_BLOCK;
                } else {
                    meltingParticleType = Material.getMaterial(MeltingParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(MeltingAmount), Double.parseDouble(MeltingPlayX),
                        Double.parseDouble(MeltingPlayY), Double.parseDouble(MeltingPlayZ), Float.parseFloat(MeltingSpeed), meltingParticleType.createBlockData());
            }
        }
    }}