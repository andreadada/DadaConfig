package me.mrbast.dadaconfig.version;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Version1718 extends Version{


    @Override
    public List<String> getComments(ConfigurationSection configSection, @NotNull String path) {
        return List.of();
    }

    @Override
    public List<String> getInlineComments(ConfigurationSection configSection, @NotNull String path) {
        return List.of();
    }

    @Override
    public void setComments(ConfigurationSection configSection, @NotNull String path, List<String> list) {
    }

    @Override
    public void setInlineComments(ConfigurationSection configSection, @NotNull String path, @Nullable List<String> comments) {
    }

}
