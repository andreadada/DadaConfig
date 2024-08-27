package me.mrbast.dadaconfig.logic;

import me.mrbast.dadaconfig.version.Version;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public abstract class Config extends ConfigSection {
    protected File configFile;



    public Config(){
        super();
    }

    public boolean init(File file) throws IOException, InvalidConfigurationException {
        this.configFile = file;
        load(configFile);
        return true;

    }

    public boolean init(String filePath, boolean loadDefault) throws IOException, InvalidConfigurationException {
        this.configFile = new File(Version.getPlugin().getDataFolder(), filePath);

        boolean created = true;
        if (!configFile.exists()) {
            boolean ignore = configFile.getParentFile().mkdirs();
            if(loadDefault) Version.getPlugin().saveResource(filePath, true);
            else created = configFile.createNewFile();
        }
        if(!created) return false;

        load(configFile);
        return true;
    }

    public boolean initDirectory(String filePath){

        File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
        File fil = new File(Version.getPlugin().getDataFolder(), filePath);
        if(!fil.exists()){
            fil.getParentFile().mkdirs();
            fil.mkdir();
        }
        if(jarFile.isFile()) {
            try {
                JarFile jar = new JarFile(jarFile);
                Enumeration<JarEntry> entries = jar.entries();
                while(entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if(!name.startsWith(filePath+"/")) continue;
                    InputStream in = getClass().getResourceAsStream("/" + name);
                    File outFile = new File(Version.getPlugin().getDataFolder(), name);
                    if(outFile.isDirectory()) continue;

                    if(!outFile.exists()){
                        outFile.getParentFile().mkdirs();
                        outFile.createNewFile();
                        OutputStream out = new FileOutputStream(outFile);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = in.read(buffer)) != -1) {
                            out.write(buffer, 0, length);
                        }
                        out.close();
                        in.close();
                    }
                }
                jar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public List<File> getAllSubFiles(File directory) {
        List<File> fileList = new ArrayList<>();
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        fileList.addAll(getAllSubFiles(file));
                    } else {
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }

    public void getAllSubFiles(String directory, Consumer<RuntimeConfig> runtimeConfigConsumer) {
        getAllSubFiles(new File(Version.getPlugin().getDataFolder(), directory), runtimeConfigConsumer);
    }

    public void getAllSubFiles(File directory, Consumer<RuntimeConfig> runtimeConfigConsumer) {
        List<File> fileList = new ArrayList<>();
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        fileList.addAll(getAllSubFiles(file));
                    } else {
                        fileList.add(file);
                    }
                }
            }
        }

        fileList.forEach(file->{
            try {
                new RuntimeConfig(file, runtimeConfigConsumer).load();
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public boolean initDirectory(String filePath, Consumer<File> fileConsumer){
        File fil = new File(Version.getPlugin().getDataFolder(), filePath);
        if(!fil.exists()){
            fil.getParentFile().mkdirs();
            fil.mkdir();
        }

        File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
        if(jarFile.isFile()) {
            try {
                JarFile jar = new JarFile(jarFile);
                Enumeration<JarEntry> entries = jar.entries();
                while(entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if(!name.startsWith(filePath+"/")) continue;
                    InputStream in = getClass().getResourceAsStream("/" + name);
                    File outFile = new File(Version.getPlugin().getDataFolder(), name);
                    if(outFile.isDirectory()) continue;

                    if(!outFile.exists()){
                        outFile.getParentFile().mkdirs();
                        outFile.createNewFile();
                        OutputStream out = new FileOutputStream(outFile);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = in.read(buffer)) != -1) {
                            out.write(buffer, 0, length);
                        }
                        out.close();
                        in.close();
                    }

                    fileConsumer.accept(outFile);

                }
                jar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;

    }
    public boolean initDirectory(String filePath, boolean loadDefault, Consumer<RuntimeConfig> config){

        File fil = new File(Version.getPlugin().getDataFolder(), filePath);

        if(!fil.exists()){
            fil.getParentFile().mkdirs();
            fil.mkdir();
        }

        if(!loadDefault) return true;

        File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
        if(jarFile.isFile()) {
            try {
                JarFile jar = new JarFile(jarFile);
                Enumeration<JarEntry> entries = jar.entries();
                while(entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if(!name.startsWith(filePath+"/")) continue;
                    InputStream in = getClass().getResourceAsStream("/" + name);
                    File outFile = new File(Version.getPlugin().getDataFolder(), name);
                    if(outFile.isDirectory()) continue;

                    if(!outFile.exists()){
                        outFile.getParentFile().mkdirs();
                        outFile.createNewFile();
                        OutputStream out = new FileOutputStream(outFile);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = in.read(buffer)) != -1) {
                            out.write(buffer, 0, length);
                        }
                        out.close();
                        in.close();
                    }

                    try{
                        new RuntimeConfig(outFile, config).load();
                    }catch (Exception e){
                        Bukkit.getServer().getLogger().warning("Error while loading file : " + outFile);
                        e.printStackTrace();
                    }


                }
                jar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public abstract void load();
    public void save(){

    }



    /*
    @Override
    public void write(String path, Object object){
        config.write(path, object);
    }

    @Override
    public <T> Optional<T> read(Class<T> clazz, String path){
        return config.read(clazz, path);
    }

     */

}