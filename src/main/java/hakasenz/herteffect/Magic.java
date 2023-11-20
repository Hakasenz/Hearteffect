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


public class Magic implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String MagicParticleType;  // 声明粒子类型变量
    private String MagicPlayX;  // 声明 X 坐标变量
    private String MagicPlayY;  // 声明 Y 坐标变量
    private String MagicPlayZ;  // 声明 Z 坐标变量
    private String MagicAmount;  // 声明粒子数量变量
    private String MagicSpeed;  // 声明粒子速度变量

    public Magic(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.MagicParticleType = config.getString("MagicParticleType", "ParticleType");
        this.MagicPlayX = config.getString("MagicPlayX", "0.0");
        this.MagicPlayY = config.getString("MagicPlayY", "0.2");
        this.MagicPlayZ = config.getString("MagicPlayZ", "0.0");
        this.MagicAmount = config.getString("MagicAmount", "10");
        this.MagicSpeed = config.getString("MagicSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Magicbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.MAGIC) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material magicParticleType;
                if (MagicParticleType == null || Material.getMaterial(MagicParticleType) == null) {
                    magicParticleType = Material.REDSTONE_BLOCK;
                } else {
                    magicParticleType = Material.getMaterial(MagicParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(MagicAmount), Double.parseDouble(MagicPlayX),
                        Double.parseDouble(MagicPlayY), Double.parseDouble(MagicPlayZ), Float.parseFloat(MagicSpeed), magicParticleType.createBlockData());
            }
        }
    }}