package me.mrbast.dadaconfig.logic;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class RuntimeConfig extends Config{



    private final Consumer<RuntimeConfig> cfg;

    private final File file;


    public RuntimeConfig(File file, Consumer<RuntimeConfig> cfg) throws IOException, InvalidConfigurationException {
        super();
        init(file);
        this.cfg = cfg;
        this.file = file;
    }

    @Override
    public void load() {
        cfg.accept(this);
    }




    public File getFile() {
        return file;
    }
}
