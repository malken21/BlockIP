package marumasa.block_ip;

import marumasa.block_ip.block.eventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class minecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new eventListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
