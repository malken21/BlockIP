package marumasa.block_ip.block;

import marumasa.block_ip.Config;
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
    private final Config config;

    public eventListener(minecraft data, Config Config) {
        minecraft = data;
        config = Config;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

        final String EventUUID = String.valueOf(event.getPlayer().getUniqueId()).replace("-","");

        //参加しようとしているプレイヤーのUUIDがConfig.ymlのExceptionUUIDにかいてあったらreturnする
        for (String uuid : config.ExceptionUUID) {
            final String ConfigUUID = uuid.replace("-","");
            if (ConfigUUID.equals(EventUUID)) return;
        }

        Collection<? extends Player> PlayerList = minecraft.getServer().getOnlinePlayers();
        String LoginIP = getIP(String.valueOf(event.getAddress()));

        int count = 0;
        for (Player player : PlayerList) {

            String OnlineIP = getIP(String.valueOf(player.getAddress()));

            if (Objects.equals(LoginIP, OnlineIP)) {
                count++;
            }
        }
        if (count >= config.max) {
            String text = String.format(config.KickText, event.getPlayer().getDisplayName(), config.max);
            event.disallow(Result.KICK_OTHER, text);
        }
    }

    public String getIP(String data) {
        return data.split("/")[1].split(":")[0];
    }
}