package me.mrbast.dadaconfig.version;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VersionLatest extends Version {


    @Override
    public List<String> getComments(ConfigurationSection configSection, @NotNull String path) {
        return configSection.getComments(path);
    }

    @Override
    public List<String> getInlineComments(ConfigurationSection configSection, @NotNull String path) {
        return configSection.getInlineComments(path);
    }

    @Override
    public void setComments(ConfigurationSection configSection, @NotNull String path, List<String> list) {
        configSection.setComments(path, list);
    }

    @Override
    public void setInlineComments(ConfigurationSection configSection, @NotNull String path, @Nullable List<String> comments) {
        configSection.setInlineComments(path, comments);
    }

}