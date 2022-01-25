package prism4291.henachoko.prism_new_mod;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

import java.io.File;

public class PrismConfig {
    public static boolean modEnabled=true;
    public static boolean testEnabled=true;
    public static int invX=0;
    public static int invY=0;
    public static double invSize=1;
    static File configFile = new File(Loader.instance().getConfigDir(), "PrismNewMod.cfg");
    static Configuration config = new Configuration(configFile);
    public static void modEnable(){
        modEnabled=true;
        saveConfig();
    }
    public static void modDisable(){
        modEnabled=false;
        saveConfig();
    }
    public static void loadConfig(){
        config.load();
        modEnabled=config.get("config", "enabled", true).getBoolean();
        testEnabled=config.get("config", "testEnabled", true).getBoolean();
        invX=config.get("config", "invX", 0).getInt();
        invY=config.get("config", "invY", 0).getInt();
        invSize=config.get("config", "invSize", 1).getDouble();
    }
    public static void saveConfig(){
        config.get("config","enabled",true).set(modEnabled);
        config.get("config","testEnabled",true).set(testEnabled);
        config.get("config", "invX", 0).set(invX);
        config.get("config", "invY", 0).set(invY);
        config.get("config", "invSize", 1).set(invSize);
        config.save();
    }

    public static void testEnable(){
        testEnabled=true;
        saveConfig();
    }
    public static void testDisable(){
        testEnabled=false;
        saveConfig();
    }
    public static void invChange(int x,int y,double size){
        invX=x;
        invY=y;
        invSize=size;
        saveConfig();
    }


}
