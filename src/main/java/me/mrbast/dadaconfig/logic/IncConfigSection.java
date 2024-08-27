package me.mrbast.dadaconfig.logic;

import me.mrbast.dadaconfig.version.Version;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class IncConfigSection extends ConfigSection{

    protected ConfigurationSection configurationSection;

    public IncConfigSection(ConfigurationSection configurationSection) {
        this.configurationSection = configurationSection;

    }


    @Override
    public String toString() {
        return "me.mrbast.dadaconfig.logic.IncConfigSection{" +
                "configurationSection=" + configurationSection +
                '}';
    }


    @NotNull
    @Override
    public ConfigurationSection createSection(@NotNull String path) {
        return configurationSection.createSection(path);
    }

    @Override
    public IncConfigSection create(String path) {
        return super.create(path);
    }

    @NotNull
    @Override
    public ConfigurationSection createSection(@NotNull String path, @NotNull Map<?, ?> map) {
        return configurationSection.createSection(path, map);
    }

    @Nullable
    @Override
    public Object get(String path) {
        return configurationSection.get(path);
    }

    @Nullable
    @Override
    public Object get(String path, @Nullable Object def) {
        return configurationSection.get(path, def);
    }

    @Nullable
    @Override
    public ConfigurationSection getConfigurationSection(@NotNull String path) {
        return configurationSection.getConfigurationSection(path);
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        return configurationSection.getBoolean(path);
    }

    @Override
    public boolean getBoolean(@NotNull String path, boolean def) {
        return configurationSection.getBoolean(path, def);
    }

    @Nullable
    @Override
    public Color getColor(@NotNull String path) {
        return configurationSection.getColor(path);
    }

    @Nullable
    @Override
    public Color getColor(@NotNull String path, @Nullable Color def) {
        return configurationSection.getColor(path, def);
    }

    @Nullable
    @Override
    public Configuration getRoot() {
        return configurationSection.getRoot();
    }

    @Nullable
    @Override
    public ConfigurationSection getDefaultSection() {
        return configurationSection.getDefaultSection();
    }

    @Nullable
    @Override
    public ConfigurationSection getParent() {
        return configurationSection.getParent();
    }

    @Override
    public double getDouble(@NotNull String path) {
        return configurationSection.getDouble(path);
    }

    @Override
    public double getDouble(@NotNull String path, double def) {
        return configurationSection.getDouble(path, def);
    }

    @Override
    public int getInt(@NotNull String path) {
        return configurationSection.getInt(path);
    }

    @Override
    public int getInt(@NotNull String path, int def) {
        return configurationSection.getInt(path, def);
    }

    @Nullable
    @Override
    public ItemStack getItemStack(@NotNull String path) {
        return configurationSection.getItemStack(path);
    }

    @Nullable
    @Override
    public ItemStack getItemStack(@NotNull String path, @Nullable ItemStack def) {
        return configurationSection.getItemStack(path, def);
    }

    @Nullable
    @Override
    public List<?> getList(@NotNull String path) {
        return configurationSection.getList(path);
    }

    @Nullable
    @Override
    public List<?> getList(@NotNull String path, @Nullable List<?> def) {
        return configurationSection.getList(path, def);
    }

    @NotNull
    @Override
    public List<Boolean> getBooleanList(@NotNull String path) {
        return configurationSection.getBooleanList(path);
    }

    @NotNull
    @Override
    public List<Byte> getByteList(@NotNull String path) {
        return configurationSection.getByteList(path);
    }

    @NotNull
    @Override
    public List<Character> getCharacterList(@NotNull String path) {
        return configurationSection.getCharacterList(path);
    }

    @NotNull
    @Override
    public List<Double> getDoubleList(@NotNull String path) {
        return configurationSection.getDoubleList(path);
    }

    @NotNull
    @Override
    public List<Float> getFloatList(@NotNull String path) {
        return configurationSection.getFloatList(path);
    }

    @NotNull
    @Override
    public List<Integer> getIntegerList(@NotNull String path) {
        return configurationSection.getIntegerList(path);
    }

    @NotNull
    @Override
    public List<Long> getLongList(@NotNull String path) {
        return configurationSection.getLongList(path);
    }

    @NotNull
    @Override
    public List<Map<?, ?>> getMapList(@NotNull String path) {
        return configurationSection.getMapList(path);
    }

    @NotNull
    @Override
    public List<Short> getShortList(@NotNull String path) {
        return configurationSection.getShortList(path);
    }

    @NotNull
    public List<String> getComments(@NotNull String path) {
        return Version.getInstance().getComments(configurationSection, path);
    }

    @NotNull
    public List<String> getInlineComments(@NotNull String path) {
        return Version.getInstance().getInlineComments(configurationSection, path);
    }

    @NotNull
    @Override
    public List<String> getStringList(@NotNull String path) {
        return configurationSection.getStringList(path);
    }

    @Nullable
    @Override
    public Location getLocation(@NotNull String path) {
        return configurationSection.getLocation(path);
    }

    @Nullable
    @Override
    public Location getLocation(@NotNull String path, @Nullable Location def) {
        return configurationSection.getLocation(path, def);
    }

    @Override
    public long getLong(@NotNull String path) {
        return configurationSection.getLong(path);
    }

    @Override
    public long getLong(@NotNull String path, long def) {
        return configurationSection.getLong(path, def);
    }

    @NotNull
    @Override
    public Map<String, Object> getValues(boolean deep) {
        return configurationSection.getValues(deep);
    }

    @Nullable
    @Override
    public OfflinePlayer getOfflinePlayer(@NotNull String path) {
        return configurationSection.getOfflinePlayer(path);
    }

    @Nullable
    @Override
    public OfflinePlayer getOfflinePlayer(@NotNull String path, @Nullable OfflinePlayer def) {
        return configurationSection.getOfflinePlayer(path, def);
    }

    @NotNull
    @Override
    public String getCurrentPath() {
        return Objects.requireNonNull(configurationSection.getCurrentPath());
    }

    @NotNull
    @Override
    public String getName() {
        return configurationSection.getName();
    }

    @Nullable
    @Override
    public String getString(@NotNull String path) {
        return configurationSection.getString(path);
    }

    @Nullable
    @Override
    public String getString(@NotNull String path, @Nullable String def) {
        return configurationSection.getString(path, def);
    }

    @Nullable
    @Override
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz) {
        return configurationSection.getObject(path, clazz);
    }

    @Nullable
    @Override
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return configurationSection.getObject(path, clazz, def);
    }

    @Nullable
    @Override
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz) {
        return configurationSection.getSerializable(path, clazz);
    }

    @Nullable
    @Override
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return configurationSection.getSerializable(path, clazz, def);
    }

    @Nullable
    @Override
    public Vector getVector(@NotNull String path) {
        return configurationSection.getVector(path);
    }

    @Nullable
    @Override
    public Vector getVector(@NotNull String path, @Nullable Vector def) {
        return configurationSection.getVector(path, def);
    }

    @NotNull
    @Override
    public Set<String> getKeys(boolean deep) {
        return configurationSection.getKeys(deep);
    }


    @Override
    public void set(@NotNull String path, @Nullable Object value) {
        configurationSection.set(path, value);
    }

    public void setComments(@NotNull String path, @Nullable List<String> comments) {
        Version.getInstance().setComments(configurationSection, path, comments);
    }

    public void setInlineComments(@NotNull String path, @Nullable List<String> comments) {
        Version.getInstance().setInlineComments(configurationSection, path, comments);
    }

    @Override
    public boolean isSet(@NotNull String path) {
        return configurationSection.isSet(path);
    }

    @Override
    public boolean contains(@NotNull String path) {
        return configurationSection.contains(path);
    }


    @Override
    public boolean contains(@NotNull String path, boolean ignoreDefault) {
        return configurationSection.contains(path, ignoreDefault);
    }
}
