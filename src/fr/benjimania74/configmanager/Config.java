package fr.benjimania74.configmanager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    protected String jar = Config.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1);
    public Path path;

    private static Config instance;
    public static Config getInstance(){return instance;}

    public Config(String addonName){
        instance = this;
        path = Paths.get((System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("Windows") ? "" : "/") + jar.replace("/" + jar.substring(jar.lastIndexOf("/") + 1), "") + "/" + addonName);
        if(!Files.exists(path)){try {Files.createDirectory(path);}catch (Exception e){e.printStackTrace();instance = null;}}
    }

    public ConfigManager createConfig(String name){
        try{
            File config = new File(path + "/" + name);
            if(config.exists()){return getConfig(config);}
            config.createNewFile();
            ConfigManager configManager = new ConfigManager(config);
            configManager.writeDefault();
            return configManager;
        }catch (Exception e){e.printStackTrace(); return null;}
    }

    public EncodedConfigManager createEncodedConfig(String name){
        try{
            File config = new File(path + "/" + name);
            if(config.exists()){return getEncodedConfig(config);}
            config.createNewFile();
            EncodedConfigManager eConfigManager = new EncodedConfigManager(config);
            eConfigManager.writeDefault();
            return eConfigManager;
        }catch (Exception e){e.printStackTrace(); return null;}
    }

    public boolean deleteConfig(String name){
        try{
            File f = new File(path + "/" + name);
            f.delete();
            return true;
        }catch (Exception e){e.printStackTrace();return false;}
    }

    public ConfigManager getConfig(String name){return getConfig(new File(path + "/" + name));}
    public ConfigManager getConfig(File file){return new ConfigManager(file);}
    public EncodedConfigManager getEncodedConfig(String name){return getEncodedConfig(new File(path + "/" + name));}
    public EncodedConfigManager getEncodedConfig(File file){return new EncodedConfigManager(file);}
}
