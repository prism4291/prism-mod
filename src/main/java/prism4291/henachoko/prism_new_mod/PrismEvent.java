package prism4291.henachoko.prism_new_mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
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
import org.lwjgl.opengl.GL11;
import prism4291.henachoko.prism_new_mod.Config.PrismConfig;

import java.util.Timer;
import java.util.TimerTask;

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
            PrismVariable.armorStandList.add(armorStand);
            PrismVariable.armorStandTime.put(armorStand.getUniqueID(), 0);


        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void ev3(RenderWorldLastEvent event) {
        if (!PrismConfig.modEnabled) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        if (player == null) {
            return;
        }
        for (EntityArmorStand armorStand : PrismVariable.armorStandList) {
            int t = PrismVariable.armorStandTime.get(armorStand.getUniqueID());
            if (t > 2000) {
                continue;
            }
            PrismVariable.armorStandTime.put(armorStand.getUniqueID(), t + 1);
            double x = (armorStand.posX - player.lastTickPosX) + ((armorStand.posX - player.posX) - (armorStand.posX - player.lastTickPosX)) * event.partialTicks;
            double y = (armorStand.posY + t * 0.005 - player.lastTickPosY) + ((armorStand.posY + t * 0.005 - player.posY) - (armorStand.posY + t * 0.005 - player.lastTickPosY)) * event.partialTicks;
            double z = (armorStand.posZ - player.lastTickPosZ) + ((armorStand.posZ - player.posZ) - (armorStand.posZ - player.lastTickPosZ)) * event.partialTicks;
            RenderManager renderManager = mc.getRenderManager();
            String text = PrismUtils.damageCompactor(armorStand.getCustomNameTag());
            int width = mc.fontRendererObj.getStringWidth(text) / 2;
            GlStateManager.pushMatrix();
            GlStateManager.translate(x, y, z);
            GL11.glNormal3f(0f, 1f, 0f);
            GlStateManager.rotate(-renderManager.playerViewY, 0f, 1f, 0f);
            GlStateManager.rotate(renderManager.playerViewX, 1f, 0f, 0f);
            GlStateManager.scale(-0.03f, -0.03f, -0.03f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            mc.fontRendererObj.drawString(text, -width, 0, 0xffffff);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();

        }

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void ev4(RenderGameOverlayEvent.Text event) {
        if (!PrismConfig.modEnabled) {
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

            double invSize=1;
            double invX=0;
            double invY=0;


            GlStateManager.pushMatrix();
            GlStateManager.translate((x * 16)*invX, (y * 16)*invY, 0);
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
