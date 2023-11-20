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


public class BlockExplode implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String BlockExplodeParticleType;  // 声明粒子类型变量
    private String BlockExplodePlayX;  // 声明 X 坐标变量
    private String BlockExplodePlayY;  // 声明 Y 坐标变量
    private String BlockExplodePlayZ;  // 声明 Z 坐标变量
    private String BlockExplodeAmount;  // 声明粒子数量变量
    private String BlockExplodeSpeed;  // 声明粒子速度变量

    public BlockExplode(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.BlockExplodeParticleType = config.getString("BlockExplodeParticleType", "ParticleType");
        this.BlockExplodePlayX = config.getString("BlockExplodePlayX", "0.0");
        this.BlockExplodePlayY = config.getString("BlockExplodePlayY", "0.2");
        this.BlockExplodePlayZ = config.getString("BlockExplodePlayZ", "0.0");
        this.BlockExplodeAmount = config.getString("BlockExplodeAmount", "10");
        this.BlockExplodeSpeed = config.getString("BlockExplodeSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("BlockExplodebutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material blockExplodeParticleType;
                if (BlockExplodeParticleType == null || Material.getMaterial(BlockExplodeParticleType) == null) {
                    blockExplodeParticleType = Material.REDSTONE_BLOCK;
                } else {
                    blockExplodeParticleType = Material.getMaterial(BlockExplodeParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(BlockExplodeAmount), Double.parseDouble(BlockExplodePlayX),
                        Double.parseDouble(BlockExplodePlayY), Double.parseDouble(BlockExplodePlayZ), Float.parseFloat(BlockExplodeSpeed), blockExplodeParticleType.createBlockData());
            }
        }
    }}