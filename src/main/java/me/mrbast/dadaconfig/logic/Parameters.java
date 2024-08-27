package me.mrbast.dadaconfig.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Parameters {

    private Map<String, Object> parameters = new HashMap<>();

    private Parameters(){

    }

    public static Parameters create(){
        return new Parameters();
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public Parameters add(String name, Object value){
        parameters.put(name, value);
        return this;
    }

    public <T> Optional<T> get(Class<T> clazz, String name){
        Object val = parameters.get(name);
        if(val == null) return Optional.empty();
        return Optional.of(clazz.cast(val));
    }


    public Parameters addAll(Parameters parameter) {
        if(parameter == null) return this;
        this.parameters.putAll(parameter.parameters);
        return this;
    }
}
