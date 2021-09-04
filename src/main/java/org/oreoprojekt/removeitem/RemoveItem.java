package org.oreoprojekt.removeitem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class RemoveItem extends JavaPlugin {

    int itemRemover = 1;

    public static String prefix2 = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "지우개" + ChatColor.GRAY + "] " + ChatColor.WHITE;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "removeItemPlugin on");
        timer(true);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "removeItemPlugin off");
        timer(false);
    }

    public void timer(boolean tf) {
        if (tf) {
            itemRemover = Bukkit.getScheduler().scheduleSyncRepeatingTask(RemoveItem.getPlugin(RemoveItem.class), new Runnable() {
                int time = 900; // 15분
                int timeleft = time;
                @Override
                public void run() {
                    if (timeleft == 30) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage(prefix2 + "30초 후 바닥의 모든 아이템이 제거됩니다.");
                            Bukkit.getConsoleSender().sendMessage(prefix2 + "30초 뒤 모든 아이템이 제거됩니다.");
                        }
                    }
                    if (timeleft <= 5 && timeleft >= 1) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage(prefix2 + timeleft + "초 후 바닥의 모든 아이템이 제거됩니다.");
                            Bukkit.getConsoleSender().sendMessage(prefix2 + timeleft + "초 뒤 모든 아이템이 제거됩니다.");
                        }
                    }
                    if (timeleft < 1) {
                        clearItem();
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage(prefix2 + "바닥에 있는 모든 아이템이 제거되었습니다.");
                        }
                        Bukkit.getConsoleSender().sendMessage(prefix2 + "모든 아이템이 제거되었습니다.");
                        timeleft = time;
                    }
                    timeleft--;
                }
            }, 0, 20);
        }
        else {
            Bukkit.getScheduler().cancelTask(itemRemover);
        }
    }

    public void clearItem() {
        Bukkit.dispatchCommand(getServer().getConsoleSender() , "kill @e[type=item]");
    }
}
