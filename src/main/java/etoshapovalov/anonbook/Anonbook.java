package etoshapovalov.anonbook;

import etoshapovalov.anonbook.cmd.AnonCheckCommand;
import etoshapovalov.anonbook.cmd.AnonCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Anonbook extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("anon").setExecutor(new AnonCommand());
        this.getCommand("anoncheck").setExecutor(new AnonCheckCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
