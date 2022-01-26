package prism4291.henachoko.prism_new_mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



@Mod(modid = PrismMod.MODID, version = PrismMod.VERSION,canBeDeactivated=true)
public class PrismMod {
    public static final String MODID = "prism_new_mod";
    public static final String VERSION = "1.8";

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {
        System.out.println("INIT PRISM");
        ClientCommandHandler.instance.registerCommand(new PrismCommandPrism());
        MinecraftForge.EVENT_BUS.register(new PrismEventTest());
        PrismConfig.loadConfig();
    }


}
