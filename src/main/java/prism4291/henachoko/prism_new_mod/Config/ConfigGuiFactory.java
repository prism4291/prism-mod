package prism4291.henachoko.prism_new_mod.Config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import org.apache.http.util.LangUtils;
import prism4291.henachoko.prism_new_mod.PrismMod;

import java.util.Set;

public class ConfigGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraft) {

    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return GuiMainConfig.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement runtimeOptionCategoryElement) {
        return null;
    }

    public static class GuiMainConfig extends GuiConfig {
        public GuiMainConfig(GuiScreen gui)
        {
            super(gui, PrismConfig.getConfigElements(), PrismMod.MODID, false, false, "Prism Config!!");
        }
    }
}
