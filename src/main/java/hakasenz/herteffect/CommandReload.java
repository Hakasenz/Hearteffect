package hakasenz.herteffect;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class CommandReload implements CommandExecutor {

    private Plugin plugin;

    public CommandReload(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hearteffect")) {
            if (sender.hasPermission("hearteffect.reload")) {
                // 重新加载配置文件
                plugin.reloadConfig();
                double voidSpeed = plugin.getConfig().getDouble("VoidSpeed");
                sender.sendMessage(ChatColor.GREEN + "[HE] 配置文件重载完成！VoidSpeed = " + voidSpeed);
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "你没有权限执行该指令！");
                sender.sendMessage(ChatColor.RED + "你需要的权限节点在config中已经写明了");
                return true;
            }
        }
        return false;
    }
}
