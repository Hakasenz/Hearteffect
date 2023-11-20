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


public class BlockFalling implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String BlockFallingParticleType;  // 声明粒子类型变量
    private String BlockFallingPlayX;  // 声明 X 坐标变量
    private String BlockFallingPlayY;  // 声明 Y 坐标变量
    private String BlockFallingPlayZ;  // 声明 Z 坐标变量
    private String BlockFallingAmount;  // 声明粒子数量变量
    private String BlockFallingSpeed;  // 声明粒子速度变量

    public BlockFalling(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.BlockFallingParticleType = config.getString("BlockFallingParticleType", "ParticleType");
        this.BlockFallingPlayX = config.getString("BlockFallingPlayX", "0.0");
        this.BlockFallingPlayY = config.getString("BlockFallingPlayY", "0.2");
        this.BlockFallingPlayZ = config.getString("BlockFallingPlayZ", "0.0");
        this.BlockFallingAmount = config.getString("BlockFallingAmount", "10");
        this.BlockFallingSpeed = config.getString("BlockFallingSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("BlockFallingbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material blockFallingParticleType;
                if (BlockFallingParticleType == null || Material.getMaterial(BlockFallingParticleType) == null) {
                    blockFallingParticleType = Material.REDSTONE_BLOCK;
                } else {
                    blockFallingParticleType = Material.getMaterial(BlockFallingParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(BlockFallingAmount), Double.parseDouble(BlockFallingPlayX),
                        Double.parseDouble(BlockFallingPlayY), Double.parseDouble(BlockFallingPlayZ), Float.parseFloat(BlockFallingSpeed), blockFallingParticleType.createBlockData());
            }
        }
    }}