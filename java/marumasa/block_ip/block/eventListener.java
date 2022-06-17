package marumasa.block_ip.block;

import marumasa.block_ip.minecraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import java.util.Collection;
import java.util.Objects;

public class eventListener implements Listener {
    private final minecraft minecraft;

    public eventListener(minecraft data) {
        minecraft = data;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

        Collection<? extends Player> PlayerList = minecraft.getServer().getOnlinePlayers();
        String LoginIP = getIP(String.valueOf(event.getAddress()));
        for (Player player : PlayerList) {

            String OnlineIP = getIP(String.valueOf(player.getAddress()));

            if (Objects.equals(LoginIP, OnlineIP)) {
                String text = String.format("すでに同IPからプレイヤーがログインしているため\n%sは参加できません", event.getPlayer().getDisplayName());
                event.disallow(Result.KICK_OTHER, text);
            }
        }
    }

    public String getIP(String data) {
        return data.split("/")[1].split(":")[0];
    }
}
