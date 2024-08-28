package me.mrbast.dadaconfig.version;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public abstract class Version {

    private static Plugin plugin;
    private static Logger LOGGER;
    private static Version instance;

    private static List<VersionInstancer> versions = new ArrayList<>();
    public static Version getInstance(){
        return instance;
    }
    static{
        versions.add(new VersionInstancer(Pattern.compile("1\\.(21.*|21|20.*|20|19.*|19)"), VersionLatest.class));
        versions.add(new VersionInstancer(Pattern.compile("1\\.(17.*|17|18.*|18)"), Version1718.class));
    }

    private static class VersionInstancer {

        private final Pattern version;
        private final Class<?> classPath;
        public VersionInstancer(Pattern version, Class<?> classPath){
            this.version = version;
            this.classPath = classPath;
        }

    }

    public static Plugin getPlugin(){
        return plugin;
    }
    public static void prepare(Plugin plugin) throws RuntimeException{

        Version.plugin = plugin;
        LOGGER = plugin.getLogger();
        String version = Bukkit.getServer().getBukkitVersion();

        Optional<VersionInstancer> opt = versions.stream().filter(ver -> ver.version.matcher(version).find()).findFirst();
        opt.ifPresentOrElse(ver->{
            try {
                instance = (Version) ver.classPath.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }, ()->{
            instance = new VersionDefault();
        });

    }

    public abstract List<String> getComments(ConfigurationSection configSection, @NotNull String path);
    public abstract List<String> getInlineComments(ConfigurationSection configSection, @NotNull String path);
    public abstract void setComments(ConfigurationSection configSection, @NotNull String path, List<String> list);
    public abstract void setInlineComments(ConfigurationSection configSection, @NotNull String path, @Nullable List<String> comments);



}