package me.mrbast.dadaconfig.logic;

import java.util.Optional;

public interface ConfigurationReader<T>{

    Optional<T> read(ConfigSection configuration, String path, Parameters parameters);
}
