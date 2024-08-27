package me.mrbast.dadaconfig.logic;

public interface ConfigurationWriter<T> {

    void write(ConfigSection configuration, String path, T object);
}
