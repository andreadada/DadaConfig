package me.mrbast.dadaconfig.logic;


import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.Vector;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConfigSection extends YamlConfiguration {


    private static final Map<Class<?>, ConfigurationWriter<?>> writers = new HashMap<>();
    private static final Map<Class<?>, ConfigurationReader<?>> readers = new HashMap<>();

    static{


        readers.put(Character.class, (sect, path, parameters)->Optional.of(Objects.requireNonNull(sect.getString(path)).charAt(0)));
        readers.put(UUID.class, (sect, path, parameters)-> Optional.of(UUID.fromString(Objects.requireNonNull(sect.getString(path)))));
        readers.put(Material.class, (sect, path, parameters)-> Optional.ofNullable(Material.getMaterial(Objects.requireNonNull(sect.getString(path != null ? path : "")))));
        readers.put(Vector.class, (ConfigurationReader<org.bukkit.util.Vector>) (configuration, path, parameters) -> {
            String val = configuration.getString(path == null ? "" : path);
            if(val == null) return Optional.empty();
            String[] split = val.split(" ");
            Number x,y,z;
            try{
                x = Double.parseDouble(split[0]);
            }catch (ClassCastException e){
                x = Integer.parseInt(split[0]);
            }
            try{
                y = Double.parseDouble(split[1]);
            }catch (ClassCastException e){
                y = Integer.parseInt(split[1]);
            }
            try{
                z = Double.parseDouble(split[2]);
            }catch (ClassCastException e){
                z = Integer.parseInt(split[2]);
            }
            return Optional.of(new org.bukkit.util.Vector(x.doubleValue(), y.doubleValue(), z.doubleValue()));
        });
        writers.put(org.bukkit.util.Vector.class, (ConfigurationWriter<org.bukkit.util.Vector>) (configuration, path, vector) -> {
            configuration.set(path == null ? "" : path, vector.getX() + " " + vector.getY() + " " + vector.getZ());
        });
        readers.put(Location.class, (sect, path, parameters)-> {
            Optional<String> world_ =sect.readString("world");
            Optional<Double> x_ = sect.readDouble("x");
            Optional<Double> y_ = sect.readDouble("y");
            Optional<Double> z_ = sect.readDouble("z");
            Optional<Float> yaw_ = sect.readFloat("yaw");
            Optional<Float> pitch_ = sect.readFloat("pitch");

            if(world_.isEmpty() || x_.isEmpty() || y_.isEmpty() || z_.isEmpty()){
                return Optional.empty();
            }

            World world = Bukkit.getWorld(world_.get());
            if(world == null) return Optional.empty();

            Location location = new Location(world, x_.get(), y_.get(), z_.get());

            yaw_.ifPresent(location::setYaw);
            pitch_.ifPresent(location::setPitch);
            return Optional.of(location);
        });

        registerWriter(Location.class, (sect, path, location)->{;
            sect.set("world",  Objects.requireNonNull(location.getWorld()).getName());
            sect.set("x", location.getX());
            sect.set("y", location.getY());
            sect.set("z", location.getZ());
            sect.set("yaw", location.getYaw());
            sect.set("pitch", location.getPitch());
        });

        readers.put(Particle.class, (sect, path, parameters)->{

            Particle particle;
            try{

                Optional<String> optParticle = sect.read(String.class, path);
                if(optParticle.isEmpty()){ return Optional.empty(); }

                particle = Particle.valueOf(optParticle.get());
            }catch (Exception e){
                return Optional.empty();
            }

            return Optional.of(particle);
        });


    }

    public static <T> void register(Class<T> clazz, ConfigurationParser<T> parser){

        readers.put(clazz, parser);
        writers.put(clazz, parser);

    }

    @SuppressWarnings("all")
    protected static <T> ConfigurationWriter getWriter(Class<? extends Object> clazz){return (ConfigurationWriter) writers.getOrDefault(clazz, ConfigurationSection::set);}


    @SuppressWarnings("all")
    protected static <T> ConfigurationReader<T> getReader(Class<T> clazz){
        return (ConfigurationReader<T>) readers.getOrDefault(clazz, (config, path, parameters)-> {
            if (path != null) return Optional.ofNullable(config.get(path));
            return Optional.ofNullable(config.get(""));
        });}

    protected static <T> void registerWriter(Class<T> clazz, ConfigurationWriter<T> writer){
        writers.put(clazz, writer);
    }

    @SuppressWarnings(value = "all")
    public void write(String path, Object object){
        getWriter(object.getClass()).write(this, path, object);
    }
    @SuppressWarnings(value = "all")
    public <T> void write(Class<T> clazz, String path, Object object){
        getWriter(clazz).write(this, path, object);
    }


    protected Optional<Long> readLong(String path) {return read(Long.class, path);}
    public Optional<Location> readLocation(String path) {return read(Location.class, path);}
    public Optional<Double> readDouble(String path) {if(!contains(path)) return Optional.empty(); return Optional.of(this.getDouble(path));}
    public Optional<Number> readNumber(String path) {if(!contains(path)) return Optional.empty(); return Optional.of(this.get(path) instanceof Number ? (Number) Objects.requireNonNull(this.get(path)) : 0);}
    public Optional<Boolean> readBoolean(String path) {return read(Boolean.class, path);}
    public Optional<Float> readFloat(String path) {if(!contains(path)) return Optional.empty(); return Optional.of((float) this.getDouble(path)); }
    public Optional<String> readString(String path) throws CouldNotReadException{return read(String.class, path);}
    public Optional<Integer> readInt(String path) throws CouldNotReadException{return read(Integer.class, path);}
    public Optional<Character> readChar(String path) {return read(Character.class, path);}
    public Optional<Vector> readVector(String path) {return read(Vector.class, path);}

    public <T> Optional<T> read(Class<T> clazz, String path) throws CouldNotReadException{
        return getReader(clazz).read(this, path, null);
    }

    public <T> Optional<T> read(Class<T> clazz, String path, Parameters parameters) throws CouldNotReadException{
        return getReader(clazz).read(this, path, parameters);
    }


    public <T> Optional<T> read(Class<T> clazz, Parameters parameters) throws CouldNotReadException{
        return getReader(clazz).read(this, null, parameters);
    }

    public <T> Optional<T> read(Class<T> clazz) throws CouldNotReadException{
        return getReader(clazz).read(this, null, null);
    }

    public Optional<Object> read(String path){
        return getReader(Object.class).read(this, path, null);
    }


    public <T, U> Optional<T> read(Class<U> converter, Class<T> clazz ) throws CouldNotReadException{

        Optional<U> val = getReader(converter).read(this, null, null);
        if(val.isEmpty()){return Optional.empty();}
        if(clazz.isAssignableFrom(val.get().getClass())){
            T value = clazz.cast(val.get());
            return Optional.of(value);
        }
        return Optional.empty();
    }



    public Collection<IncConfigSection> getNodes(){
        Set<String> set = new LinkedHashSet<>(this.getKeys(false));
        //Debugger.log(Debugger.HIGH, "nodes: " + this.getName() + " has " + set);
        return set.stream().map(this::getSectionUnchecked).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Collection<IncConfigSection> getNodes(Predicate<String> filter){
        Set<String> set = this.getKeys(false).stream().filter(filter).collect(Collectors.toCollection(LinkedHashSet::new));
        //Debugger.log(Debugger.HIGH, "nodes: " + this.getName() + " has " + set);
        return set.stream().map(this::getSectionUnchecked).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public IncConfigSection moveTo(String section){
        return new IncConfigSection(this.getConfigurationSection(section));
    }

    public IncConfigSection getSectionUnchecked(String section){
        return new IncConfigSection(this.getConfigurationSection(section));

    }

    public IncConfigSection create(String path) {
        return new IncConfigSection(this.createSection(path));
    }


    public Optional<IncConfigSection> getSection(String section){
        ConfigurationSection sect = this.getConfigurationSection(section);
        //Debugger.log(Debugger.HIGH, "Section: " + section + " is " + sect);
        return sect == null ? Optional.empty() : Optional.of(new IncConfigSection(sect));

    }

}
