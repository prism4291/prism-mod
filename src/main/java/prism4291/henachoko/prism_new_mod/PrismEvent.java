package prism4291.henachoko.prism_new_mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import prism4291.henachoko.prism_new_mod.Config.PrismConfig;

public class PrismEvent {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void tickEvent(TickEvent.ClientTickEvent event) {
        if (!PrismConfig.modEnabled) {
            return;
        }
        PrismVariable.currentTime = System.currentTimeMillis();
        if (PrismVariable.currentTime - PrismVariable.lastCurrentTime > 60000) {
            PrismVariable.lastCurrentTime = PrismVariable.currentTime;
            PrismVariable.uuidList.subList(0, PrismVariable.uuidDeleteLength).clear();
            PrismVariable.uuidDeleteLength = PrismVariable.uuidList.size();
            PrismVariable.armorStandList.subList(0, PrismVariable.armorStandDeleteLength).clear();
            PrismVariable.armorStandDeleteLength = PrismVariable.armorStandList.size();
            System.out.println("UUID Length " + PrismVariable.uuidDeleteLength + " armorStand Length " + PrismVariable.armorStandDeleteLength);
        }

    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void renderArmorStandEvent(RenderLivingEvent.Pre<EntityLivingBase> event) {
        if (!PrismConfig.modEnabled) {
            return;
        }
        if (!PrismConfig.damageEnabled) {
            return;
        }
        if (PrismVariable.uuidList.contains(event.entity.getUniqueID())) {
            return;
        }
        PrismVariable.uuidList.add(event.entity.getUniqueID());
        if (event.entity instanceof EntityArmorStand) {
            if (event.entity.getCustomNameTag().equals("")) {
                return;
            }

            //if (!armorStand.getCustomNameTag().replaceAll("\\u00A7.", "").matches(".*[0-9]{4}.*")) {
            //    return;
            //}
            //if (armorStand.getCustomNameTag().replaceAll("\\u00A7.", "").matches(".*/.*")) {
            //    return;
            //}
            if (!PrismUtils.matchDamage(event.entity.getCustomNameTag())) {
                return;
            }

            /*
            String msg = "[Prism Mod] " + armorStand.getCustomNameTag();
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            if (player != null) {
                player.addChatMessage(new ChatComponentText(msg));
            }
            */
            PrismVariable.armorStandList.add(new PrismVariable.damageIndicator(event.entity));


        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void ev3(RenderWorldLastEvent event) {
        PrismVariable.ticks = event.partialTicks;
        if (!PrismConfig.modEnabled) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        if (player == null) {
            return;
        }
        if (PrismConfig.damageEnabled) {
            for (PrismVariable.damageIndicator indicator : PrismVariable.armorStandList) {
                if (PrismVariable.currentTime - indicator.time > PrismConfig.damageIndicatorLifespan) {
                    continue;
                }

                indicator.indicatorY += (Math.cos((PrismVariable.currentTime - indicator.time) * 0.001) + 3) * 0.001;
                PrismUtils.drawIndicator(indicator.indicatorX, indicator.indicatorY, indicator.indicatorZ, indicator.text);


            }
        }

        if (PrismConfig.gridEnabled) {
            double l = 0;
        /*
        PrismUtils.drawLine(0,0.01,0,0.3,0.01,0.3);
        PrismUtils.drawLine(0,0.01,0,-0.3,0.01,0.3);
        PrismUtils.drawLine(0,0.01,0,-0.3,0.01,-0.3);
        PrismUtils.drawLine(0,0.01,0,0.3,0.01,-0.3);
        */
            if (player.onGround) {
                PrismVariable.groundY = player.posY;
            }
            //double dy=-(player.posY-PrismVariable.groundY);
            double dy = 0;
            GL11.glPushMatrix();
            PrismUtils.drawLine(0.3 + l, 0.01 + dy, 0.3, -0.3 - l, 0.01 + dy, 0.3);
            PrismUtils.drawLine(-0.3, 0.01 + dy, 0.3 + l, -0.3, 0.01 + dy, -0.3 - l);
            PrismUtils.drawLine(-0.3 - l, 0.01 + dy, -0.3, 0.3 + l, 0.01 + dy, -0.3);
            PrismUtils.drawLine(0.3, 0.01 + dy, -0.3 - l, 0.3, 0.01 + dy, 0.3 + l);
        /*
        PrismUtils.drawLine(0.3,0.01-l,0.3,0.3,0.01+l,0.3);
        PrismUtils.drawLine(-0.3,0.01-l,0.3,-0.3,0.01+l,0.3);
        PrismUtils.drawLine(-0.3,0.01-l,-0.3,-0.3,0.01+l,-0.3);
        PrismUtils.drawLine(0.3,0.01-l,-0.3,0.3,0.01+l,-0.3);

         */
            GL11.glPopMatrix();
            l = 1.5;
            GL11.glPushMatrix();
            double dx = -(player.posX % 1 > 0.5 ? player.posX % 1 - 0.5 : player.posX % 1 + 0.5);

            double dz = -(player.posZ % 1 > 0.5 ? player.posZ % 1 - 0.5 : player.posZ % 1 + 0.5);
            PrismUtils.drawLine(0.3 + l, 0.01 + dy, 0.5 + dz, -0.3 - l, 0.01 + dy, 0.5 + dz);
            PrismUtils.drawLine(-0.5 + dx, 0.01 + dy, 0.3 + l, -0.5 + dx, 0.01 + dy, -0.3 - l);
            PrismUtils.drawLine(-0.3 - l, 0.01 + dy, -0.5 + dz, 0.3 + l, 0.01 + dy, -0.5 + dz);
            PrismUtils.drawLine(0.5 + dx, 0.01 + dy, -0.3 - l, 0.5 + dx, 0.01 + dy, 0.3 + l);
            dx += 1;
            dz += 1;
            if (dz < 1) {
                PrismUtils.drawLine(0.3 + l, 0.01 + dy, 0.5 + dz, -0.3 - l, 0.01 + dy, 0.5 + dz);
            }
            PrismUtils.drawLine(-0.5 + dx, 0.01 + dy, 0.3 + l, -0.5 + dx, 0.01 + dy, -0.3 - l);
            PrismUtils.drawLine(-0.3 - l, 0.01 + dy, -0.5 + dz, 0.3 + l, 0.01 + dy, -0.5 + dz);

            if (dx < 1) {
                PrismUtils.drawLine(0.5 + dx, 0.01 + dy, -0.3 - l, 0.5 + dx, 0.01 + dy, 0.3 + l);
            }
            dx -= 2;
            dz -= 2;
            PrismUtils.drawLine(0.3 + l, 0.01 + dy, 0.5 + dz, -0.3 - l, 0.01 + dy, 0.5 + dz);
            if (dx > 1) {
                PrismUtils.drawLine(-0.5 + dx, 0.01 + dy, 0.3 + l, -0.5 + dx, 0.01 + dy, -0.3 - l);
            }
            if (dz > 1) {
                PrismUtils.drawLine(-0.3 - l, 0.01 + dy, -0.5 + dz, 0.3 + l, 0.01 + dy, -0.5 + dz);
            }
            PrismUtils.drawLine(0.5 + dx, 0.01 + dy, -0.3 - l, 0.5 + dx, 0.01 + dy, 0.3 + l);


            GL11.glPopMatrix();

        }

    }
    /*
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void ev3_2(RenderWorldLastEvent event) {
        if (!PrismConfig.modEnabled) {
            return;
        }
        if(!PrismConfig.gridEnabled){
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        if (player == null) {
            return;
        }

    }*/

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void ev4(RenderGameOverlayEvent.Text event) {
        if (!PrismConfig.modEnabled) {
            return;
        }
        if (!PrismConfig.invEnabled) {
            return;
        }
        if (event.isCanceled()) {
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

            double invSize = PrismConfig.invSize;
            int invX = PrismConfig.invX;
            int invY = PrismConfig.invY;


            GlStateManager.pushMatrix();
            GlStateManager.translate((x * 16 * invSize) + invX, (y * 16 * invSize) + invY, 0);
            GlStateManager.enableBlend();
            GlStateManager.scale(invSize, invSize, 1);
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
