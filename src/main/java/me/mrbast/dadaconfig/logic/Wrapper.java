package me.mrbast.dadaconfig.logic;

import java.util.function.Consumer;

public class Wrapper<T>{

    private T object;

    public Wrapper(T object){
        this.object = object;
    }
    public Wrapper(){

    }


    public T get() {
        return object;
    }

    public void set(T object) {
        this.object = object;
    }

    public void ifPresent(Consumer<T> consumer) {

        consumer.accept(object);

    }
}
