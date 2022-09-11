package marumasa.block_ip;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    public final String KickText;
    public final int max;
    public final List<String> ExceptionUUID;

    public Config(minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        KickText = config.getString("KickText", "すでに同じIPからプレイヤーがログインしているため\n%sは参加できません\n現在サーバーには同じIPから最大%d人まで参加できます");
        max = config.getInt("max", 2);
        ExceptionUUID = config.getStringList("ExceptionUUID");
    }
}