package prism4291.henachoko.prism_new_mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import prism4291.henachoko.prism_new_mod.Config.PrismConfig;

public class PrismEvent {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void ev2(RenderLivingEvent.Post<EntityLiving> event) {
        if (PrismVariable.uuidList.contains(event.entity.getUniqueID())) {
            return;
        }
        PrismVariable.uuidList.add(event.entity.getUniqueID());
        if (!PrismConfig.modEnabled) {
            return;
        }
        if(!PrismConfig.damageEnabled){
            return;
        }
        if (event.entity instanceof EntityArmorStand) {
            EntityArmorStand armorStand = (EntityArmorStand) event.entity;
            if (armorStand.getCustomNameTag().equals("")) {
                return;
            }

            //if (!armorStand.getCustomNameTag().replaceAll("\\u00A7.", "").matches(".*[0-9]{4}.*")) {
            //    return;
            //}
            //if (armorStand.getCustomNameTag().replaceAll("\\u00A7.", "").matches(".*/.*")) {
            //    return;
            //}
            if(!PrismUtils.matchDamage(armorStand.getCustomNameTag())){
                return;
            }

            /*
            String msg = "[Prism Mod] " + armorStand.getCustomNameTag();
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            if (player != null) {
                player.addChatMessage(new ChatComponentText(msg));
            }
            */
            PrismVariable.armorStandList.add(new PrismVariable.damageIndicator(armorStand));


        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void ev3(RenderWorldLastEvent event) {
        PrismVariable.ticks=event.partialTicks;
        if (!PrismConfig.modEnabled) {
            return;
        }
        if(!PrismConfig.damageEnabled){
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        if (player == null) {
            return;
        }
        long now= System.currentTimeMillis();
        for (PrismVariable.damageIndicator indicator : PrismVariable.armorStandList) {
            System.out.println(now-indicator.time);
            if (now-indicator.time > PrismConfig.damageIndicatorLifespan) {
                continue;
            }

            //indicator.indicatorY+=(now-indicator.lastTime)*0.001;
            indicator.lastTime=now;
            PrismUtils.drawIndicator(indicator.indicatorX, indicator.indicatorY, indicator.indicatorZ, indicator.text);


        }

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void ev4(RenderGameOverlayEvent.Text event) {
        if (!PrismConfig.modEnabled) {
            return;
        }
        if(!PrismConfig.invEnabled){
            return;
        }
        if(event.isCanceled()){
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        if (player == null) {
            return;
        }

        GlStateManager.enableDepth();
        for (int i = 0; i < 27; i++) {
            ItemStack stack = player.inventory.getStackInSlot(i + 9);
            if (stack == null) {
                continue;
            }

            int x = (i % 9);
            int y = (i / 9);

            double invSize=PrismConfig.invSize;
            int invX=PrismConfig.invX;
            int invY=PrismConfig.invY;


            GlStateManager.pushMatrix();
            GlStateManager.translate((x * 16*invSize)+invX, (y * 16*invSize)+invY, 0);
            GlStateManager.enableBlend();
            GlStateManager.scale(invSize,invSize,1);
            PrismUtils.drawItemStackWithText(stack, 0, 0, String.valueOf(stack.stackSize));
            GlStateManager.popMatrix();

        }
        /*
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        GlStateManager.enableLighting();
        mc.fontRendererObj.drawString("strA",20,20,0);
        mc.fontRendererObj.drawString("strB",20,40,0);
        GlStateManager.scale(4,2,10);
        mc.fontRendererObj.drawString("strC",20,20,0);
        GlStateManager.popAttrib();
        mc.fontRendererObj.drawString("strD",20,40,0);
        GlStateManager.scale(0.5,0.5,2);
        mc.fontRendererObj.drawString("stE",20,20,0);
        mc.fontRendererObj.drawString("stF",20,40,0);
        GlStateManager.scale(1,1,0.5);
        GlStateManager.popMatrix();
        */

    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(PrismMod.MODID)) {
            PrismConfig.syncConfig(false);
        }
    }
}
