package me.mrbast.dadaconfig.logic;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public abstract class ReturnableConfig<T> extends ConfigSection{



    public abstract CompletableFuture<T> loadAsync();
    public abstract T load();
    public abstract CompletableFuture<T> saveAsync();
    public abstract T save();

}
