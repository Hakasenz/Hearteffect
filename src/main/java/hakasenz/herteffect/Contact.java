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


public class Contact implements Listener {
    private FileConfiguration config;
    private Plugin plugin;
    private String ContactParticleType;  // 声明粒子类型变量
    private String ContactPlayX;  // 声明 X 坐标变量
    private String ContactPlayY;  // 声明 Y 坐标变量
    private String ContactPlayZ;  // 声明 Z 坐标变量
    private String ContactAmount;  // 声明粒子数量变量
    private String ContactSpeed;  // 声明粒子速度变量

    public Contact(FileConfiguration config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.ContactParticleType = config.getString("ContactParticleType", "ParticleType");
        this.ContactPlayX = config.getString("ContactPlayX", "0.0");
        this.ContactPlayY = config.getString("ContactPlayY", "0.2");
        this.ContactPlayZ = config.getString("ContactPlayZ", "0.0");
        this.ContactAmount = config.getString("ContactAmount", "10");
        this.ContactSpeed = config.getString("ContactSpeed", "1");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        boolean shouldExecute = config.getBoolean("Contactbutton", true);
        if (shouldExecute && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                Location location = player.getLocation();
                World world = location.getWorld();

                Material contactParticleType;
                if (ContactParticleType == null || Material.getMaterial(ContactParticleType) == null) {
                    contactParticleType = Material.REDSTONE_BLOCK;
                } else {
                    contactParticleType = Material.getMaterial(ContactParticleType);
                }

                // 在玩家当前位置播放粒子效果
                world.spawnParticle(Particle.BLOCK_CRACK, location, Integer.parseInt(ContactAmount), Double.parseDouble(ContactPlayX),
                        Double.parseDouble(ContactPlayY), Double.parseDouble(ContactPlayZ), Float.parseFloat(ContactSpeed), contactParticleType.createBlockData());
            }
        }
    }}