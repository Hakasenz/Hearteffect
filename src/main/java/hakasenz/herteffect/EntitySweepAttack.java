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


public class EntitySweepAttack implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String EntitySweepAttackParticleType;  // 声明粒子类型变量
    private String EntitySweepAttackPlayX;  // 声明 X 坐标变量
    private String EntitySweepAttackPlayY;  // 声明 Y 坐标变量
    private String EntitySweepAttackPlayZ;  // 声明 Z 坐标变量
    private String EntitySweepAttackAmount;  // 声明粒子数量变量
    private String EntitySweepAttackSpeed;  // 声明粒子速度变量

    public EntitySweepAttack(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.EntitySweepAttackParticleType = config.getString("EntitySweepAttackParticleType", "ParticleType");
        this.EntitySweepAttackPlayX = config.getString("EntitySweepAttackPlayX", "0.0");
        this.EntitySweepAttackPlayY = config.getString("EntitySweepAttackPlayY", "0.2");
        this.EntitySweepAttackPlayZ = config.getString("EntitySweepAttackPlayZ", "0.0");
        this.EntitySweepAttackAmount = config.getString("EntitySweepAttackAmount", "10");
        this.EntitySweepAttackSpeed = config.getString("EntitySweepAttackSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("EntitySweepAttackbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material entitySweepAttackParticleType;
                if (EntitySweepAttackParticleType == null || Material.getMaterial(EntitySweepAttackParticleType) == null) {
                    entitySweepAttackParticleType = Material.REDSTONE_BLOCK;
                } else {
                    entitySweepAttackParticleType = Material.getMaterial(EntitySweepAttackParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(EntitySweepAttackAmount), Double.parseDouble(EntitySweepAttackPlayX),
                        Double.parseDouble(EntitySweepAttackPlayY), Double.parseDouble(EntitySweepAttackPlayZ), Float.parseFloat(EntitySweepAttackSpeed), entitySweepAttackParticleType.createBlockData());
            }
        }
    }}