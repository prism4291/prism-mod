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
    public static float damageIndicatorLifespan;
    public static boolean damageEnabled;
    public static String damageCode;
    public static boolean invEnabled;
    public static int invX;
    public static int invY;
    public static double invSize;

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
        prop.comment="if events(except config event) is enabled";
        propOrder.add(prop.getName());

        prop = getProperty(Configuration.CATEGORY_GENERAL, "Hypixel API Key", "");
        hypixelApiKey = prop.getString();
        prop.comment="not used";
        propOrder.add(prop.getName());

        prop = getProperty(Configuration.CATEGORY_GENERAL, "Enable Damage Indicator", true);
        damageEnabled = prop.getBoolean();
        prop.comment="you can see Damage";
        propOrder.add(prop.getName());

        prop = getProperty(Configuration.CATEGORY_GENERAL, "Damage Indicator Color", "---a956dbccc");
        damageCode = prop.getString();
        prop.comment="color code each damage(12 length 0-f String or - to ignore\nbtw 10 length is enough)";
        propOrder.add(prop.getName());

        prop = getProperty(Configuration.CATEGORY_GENERAL, "Damage Indicator lifespan millisecond", 20000);
        damageIndicatorLifespan = prop.getInt();
        prop.comment="20000 is 20 seconds";
        propOrder.add(prop.getName());



        prop = getProperty(Configuration.CATEGORY_GENERAL, "Inventory Enabled", true);
        invEnabled = prop.getBoolean();
        prop.comment="hud inventory";
        propOrder.add(prop.getName());

        prop = getProperty(Configuration.CATEGORY_GENERAL, "invX", 0);
        invX = prop.getInt();
        prop.comment="x";
        propOrder.add(prop.getName());

        prop = getProperty(Configuration.CATEGORY_GENERAL, "invY", 0);
        invY = prop.getInt();
        prop.comment="y";
        propOrder.add(prop.getName());

        prop = getProperty(Configuration.CATEGORY_GENERAL, "invSize", 1d);
        invSize = prop.getDouble();
        prop.comment="size";
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
    public static Property getProperty(String category, String name, double defaultValue) {
        return config.get(category, name, defaultValue);
    }

}
