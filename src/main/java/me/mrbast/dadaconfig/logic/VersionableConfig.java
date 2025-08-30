package me.mrbast.dadaconfig.logic;

import me.mrbast.dadaconfig.version.Version;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class VersionableConfig extends Config{


    @Override
    public boolean initDirectory(String filePath) {


        return super.initDirectory(filePath);


    }

    public Collection<File> getDefaultFiles(String filePath, File jarFile){

        List<File> fileList = new ArrayList<>();

        Set<String> notValid = new HashSet<>();

        if(jarFile.isFile()) {
            try {
                JarFile jar = new JarFile(jarFile);
                Enumeration<JarEntry> entries = jar.entries();
                while(entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if(name.endsWith("config.properties")) continue;
                    if(!name.startsWith(filePath+"/")) continue;
                    InputStream in = getClass().getResourceAsStream("/" + name);
                    File outFile = new File(Version.getPlugin().getDataFolder(), name);
                    if (entry.isDirectory()) {
                        if (!outFile.exists()) {

                            // Costruisci il path del file config.properties
                            String configPath = entry.getName() + "config.properties"; // es. "test/subdir1/config.properties"
                            JarEntry configEntry = jar.getJarEntry(configPath);

                            if(configEntry == null) continue;

                            try (InputStream inz = jar.getInputStream(configEntry)) {
                                Properties props = new Properties();
                                props.load(inz);
                                String pattern = props.getProperty("pattern");



                                if (pattern != null && !Bukkit.getServer().getBukkitVersion().matches(pattern)) {
                                    String dirName = entry.getName().substring(entry.getName().lastIndexOf('/', entry.getName().length() - 2) + 1, entry.getName().length() - 1);

                                    notValid.add(dirName);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }



                        }
                        continue;
                    }
                    if(outFile.isDirectory()) continue;

                    if(!outFile.exists()){
                        if(notValid.contains(outFile.getParentFile().getName())) continue;

                        outFile = new File(Version.getPlugin().getDataFolder(), filePath + "/"+ outFile.getName());

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

                    fileList.add(outFile);
                }
                jar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileList;
    }



}
