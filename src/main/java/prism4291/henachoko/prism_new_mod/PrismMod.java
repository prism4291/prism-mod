package prism4291.henachoko.prism_new_mod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import prism4291.henachoko.prism_new_mod.Config.PrismConfig;

import java.io.File;


@Mod(modid = PrismMod.MODID, version = PrismMod.VERSION,clientSideOnly = true,guiFactory = "prism4291.henachoko.prism_new_mod.Config.ConfigGuiFactory")
public class PrismMod {
    public static final String MODID = "prism_new_mod";
    public static final String VERSION = "1.10";

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {
        System.out.println("INIT PRISM");
        MinecraftForge.EVENT_BUS.register(new PrismEvent());
        PrismConfig.init(new File(Loader.instance().getConfigDir(), "PrismNewMod.cfg"));
    }






}
