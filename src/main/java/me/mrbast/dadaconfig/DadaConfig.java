package me.mrbast.dadaconfig;

import me.mrbast.dadaconfig.version.Version;
import org.bukkit.plugin.Plugin;

public class DadaConfig {


    public static void prepare(Plugin  plugin){
        Version.prepare(plugin);
    }

}
