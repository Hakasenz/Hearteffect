package hakasenz.herteffect;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;


public class EntityAttack implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String EntityAttackParticleType;  // 声明粒子类型变量
    private String EntityAttackPlayX;  // 声明 X 坐标变量
    private String EntityAttackPlayY;  // 声明 Y 坐标变量
    private String EntityAttackPlayZ;  // 声明 Z 坐标变量
    private String EntityAttackAmount;  // 声明粒子数量变量
    private String EntityAttackSpeed;  // 声明粒子速度变量

    public EntityAttack(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.EntityAttackParticleType = config.getString("EntityAttackParticleType", "ParticleType");
        this.EntityAttackPlayX = config.getString("EntityAttackPlayX", "0.0");
        this.EntityAttackPlayY = config.getString("EntityAttackPlayY", "0.2");
        this.EntityAttackPlayZ = config.getString("EntityAttackPlayZ", "0.0");
        this.EntityAttackAmount = config.getString("EntityAttackAmount", "10");
        this.EntityAttackSpeed = config.getString("EntityAttackSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("EntityAttackbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material entityAttackParticleType;
                if (EntityAttackParticleType == null || Material.getMaterial(EntityAttackParticleType) == null) {
                    entityAttackParticleType = Material.REDSTONE_BLOCK;
                } else {
                    entityAttackParticleType = Material.getMaterial(EntityAttackParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(EntityAttackAmount), Double.parseDouble(EntityAttackPlayX),
                        Double.parseDouble(EntityAttackPlayY), Double.parseDouble(EntityAttackPlayZ), Float.parseFloat(EntityAttackSpeed), entityAttackParticleType.createBlockData());
            }
        }
    }}