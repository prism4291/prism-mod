package prism4291.henachoko.prism_new_mod.Config;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PrismConfig {
    private static Configuration config;

    public static String hypixelApiKey;
    public static boolean modEnabled;
    public static int invX;
    public static int invY;

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig(true);
    }
    public static void syncConfig(boolean load) {
        if (!config.isChild && load) {
            config.load();
        }
        config.setCategoryPropertyOrder(Configuration.CATEGORY_GENERAL,addConfig());
        //config.setCategoryPropertyOrder(MAIN_SETTINGS, addMainSetting());
        //config.setCategoryPropertyOrder(MAIN_SETTINGS, addSubSetting());
        if (config.hasChanged()) {
            config.save();
        }
    }


    private static List<String> addConfig() {
        Property prop;
        List<String> propOrder = new ArrayList<>();

        prop = getProperty(Configuration.CATEGORY_GENERAL, "Enabled", true);
        modEnabled = prop.getBoolean();
        propOrder.add(prop.getName());

        prop = getProperty(Configuration.CATEGORY_GENERAL, "Hypixel API Key", "");
        hypixelApiKey = prop.getString();
        propOrder.add(prop.getName());



        return propOrder;
    }

    public static List<IConfigElement> getConfigElements()
    {
        return new ArrayList<>(new ConfigElement(config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements());
    }
    public static Property getProperty(String category, String name, boolean defaultValue) {
        return config.get(category, name, defaultValue);
    }
    public static Property getProperty(String category, String name, String defaultValue) {
        return config.get(category, name, defaultValue);
    }
    public static Property getProperty(String category, String name, int defaultValue) {
        return config.get(category, name, defaultValue);
    }

}
