package hakasenz.herteffect;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class HeartEffect extends JavaPlugin {

    @Override
    public void onEnable() {

        // 检查config文件是否存在，如果不存在则创建默认的config文件
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        // 注册命令执行器
        CommandReload commandExecutor = new CommandReload(this);
        getCommand("hearteffect").setExecutor(commandExecutor);

        FileConfiguration config = getConfig();

        // 注册监听器并递配置文件和插件实例
        EntityAttack entityAttack = new EntityAttack(config, this);
        getServer().getPluginManager().registerEvents(entityAttack, this);
        Falldown falldownListener = new Falldown(config, this);
        getServer().getPluginManager().registerEvents(falldownListener, this);
        BlockExplode blockExplode = new BlockExplode(config, this);
        getServer().getPluginManager().registerEvents(blockExplode, this);
        BlockFalling blockfalling = new BlockFalling(config, this);
        getServer().getPluginManager().registerEvents(blockfalling, this);
        Contact contact = new Contact(config, this);
        getServer().getPluginManager().registerEvents(contact, this);
        Cramming cramming = new Cramming(config, this);
        getServer().getPluginManager().registerEvents(cramming, this);
        Custom custom = new Custom(config, this);
        getServer().getPluginManager().registerEvents(custom, this);
        DragonBreath dragonBreath = new DragonBreath(config, this);
        getServer().getPluginManager().registerEvents(dragonBreath, this);
        Drowning drowning = new Drowning(config, this);
        getServer().getPluginManager().registerEvents(drowning, this);
        EntityExplode entityExplode = new EntityExplode(config, this);
        getServer().getPluginManager().registerEvents(entityExplode, this);
        EntitySweepAttack entitySweepAttack = new EntitySweepAttack(config, this);
        getServer().getPluginManager().registerEvents(entitySweepAttack, this);
        Fire fire = new Fire(config, this);
        getServer().getPluginManager().registerEvents(fire, this);
        FireTick fireTick = new FireTick(config, this);
        getServer().getPluginManager().registerEvents(fireTick, this);
        Freeze freeze = new Freeze(config, this);
        getServer().getPluginManager().registerEvents(freeze, this);
        hot_floor hotFloor = new hot_floor(config, this);
        getServer().getPluginManager().registerEvents(hotFloor, this);
        Kill kill = new Kill(config, this);
        getServer().getPluginManager().registerEvents(kill, this);
        Lava lava = new Lava(config, this);
        getServer().getPluginManager().registerEvents(lava, this);
        Lightning lightning = new Lightning(config, this);
        getServer().getPluginManager().registerEvents(lightning, this);
        Magic magic = new Magic(config, this);
        getServer().getPluginManager().registerEvents(magic, this);
        Melting melting = new Melting(config, this);
        getServer().getPluginManager().registerEvents(melting, this);
        Poison poison = new Poison(config, this);
        getServer().getPluginManager().registerEvents(poison, this);
        Projectile projectile = new Projectile(config, this);
        getServer().getPluginManager().registerEvents(projectile, this);
        SonicBoom sonicBoom = new SonicBoom(config, this);
        getServer().getPluginManager().registerEvents(sonicBoom, this);
        Starvation starvation = new Starvation(config, this);
        getServer().getPluginManager().registerEvents(starvation, this);
        Suffocation suffocation = new Suffocation(config, this);
        getServer().getPluginManager().registerEvents(suffocation, this);
        Suicide suicide = new Suicide(config, this);
        getServer().getPluginManager().registerEvents(suicide, this);
        Thorns thorns = new Thorns(config, this);
        getServer().getPluginManager().registerEvents(thorns, this);
        Void Void = new Void(config, this);
        getServer().getPluginManager().registerEvents(Void, this);
        Wither wither = new Wither(config, this);
        getServer().getPluginManager().registerEvents(wither, this);
        WorldBorder worldBorder = new WorldBorder(config, this);
        getServer().getPluginManager().registerEvents(worldBorder, this);

    }
    @Override
    public void onDisable() {
        // 插件禁用时的清理工作
    }

}