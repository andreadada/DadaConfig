package me.mrbast.dadaconfig.logic;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public abstract class ReturnableConfig<T> extends ConfigSection{



    public abstract CompletableFuture<T> loadAsync();
    public abstract T load();
    public abstract CompletableFuture<Void> saveAsync(T object);
    public abstract void save(T object);

}
