package marumasa.block_ip;

import marumasa.block_ip.block.eventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class minecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        Config config = new Config(this);
        getServer().getPluginManager().registerEvents(new eventListener(this,config), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}