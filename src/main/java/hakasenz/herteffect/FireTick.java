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


public class FireTick implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String FireTickParticleType;  // 声明粒子类型变量
    private String FireTickPlayX;  // 声明 X 坐标变量
    private String FireTickPlayY;  // 声明 Y 坐标变量
    private String FireTickPlayZ;  // 声明 Z 坐标变量
    private String FireTickAmount;  // 声明粒子数量变量
    private String FireTickSpeed;  // 声明粒子速度变量

    public FireTick(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.FireTickParticleType = config.getString("FireTickParticleType", "ParticleType");
        this.FireTickPlayX = config.getString("FireTickPlayX", "0.0");
        this.FireTickPlayY = config.getString("FireTickPlayY", "0.2");
        this.FireTickPlayZ = config.getString("FireTickPlayZ", "0.0");
        this.FireTickAmount = config.getString("FireTickAmount", "10");
        this.FireTickSpeed = config.getString("FireTickSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("FireTickbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material fireTickParticleType;
                if (FireTickParticleType == null || Material.getMaterial(FireTickParticleType) == null) {
                    fireTickParticleType = Material.REDSTONE_BLOCK;
                } else {
                    fireTickParticleType = Material.getMaterial(FireTickParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(FireTickAmount), Double.parseDouble(FireTickPlayX),
                        Double.parseDouble(FireTickPlayY)  , Double.parseDouble(FireTickPlayZ), Float.parseFloat(FireTickSpeed), fireTickParticleType.createBlockData());
            }
        }
    }}