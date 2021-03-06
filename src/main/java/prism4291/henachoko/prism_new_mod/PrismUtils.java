package prism4291.henachoko.prism_new_mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import prism4291.henachoko.prism_new_mod.Config.PrismConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrismUtils {
    public static void drawLine(double x,double y,double z,double x2,double y2,double z2){
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4d(0.5,0.5,0.5,0.5);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(x,y,z);
        GL11.glVertex3d(x2,y2,z2);
        GL11.glEnd();
        GL11.glPopAttrib();
    }
    public static void drawIndicator(double x,double y,double z,String text){
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        double wx = (x - player.lastTickPosX) + ((x - player.posX) - (x - player.lastTickPosX)) * PrismVariable.ticks;
        double wy = (y - player.lastTickPosY) + ((y- player.posY) - (y- player.lastTickPosY)) * PrismVariable.ticks;
        double wz = (z - player.lastTickPosZ) + ((z - player.posZ) - (z - player.lastTickPosZ)) * PrismVariable.ticks;
        RenderManager renderManager = mc.getRenderManager();
        int width = mc.fontRendererObj.getStringWidth(text) / 2;
        GlStateManager.pushMatrix();
        GlStateManager.translate(wx, wy, wz);
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
    public static void drawItemStackWithText(ItemStack stack, int x, int y, String text) {
        if(stack == null)return;

        RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();

        RenderHelper.enableGUIStandardItemLighting();
        itemRender.renderItemAndEffectIntoGUI(stack, x, y);
        itemRender.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, stack, x, y, text);
        RenderHelper.disableStandardItemLighting();
    }
    public static boolean matchDamage(String dmg){
        if(!damageDeleteCode(dmg).matches("\\d+")){
            return false;
        }
        String cc=PrismConfig.damageCode+"777aaaeeeccc".substring(PrismConfig.damageCode.length());
        if('-'==cc.charAt(damageDeleteCode(dmg).length()-1)){
            return false;
        }
        return true;
    }
    public static String damageDeleteCode(String dmg){
    //???|???|???|???|
    dmg=dmg.replaceAll("\u00a7.","");
    return dmg.replaceAll("[\u2727\u2604\u2764\u265e]","");
    }
    public static String damageCompactor(String dmg){
        dmg=damageDeleteCode(dmg);
        int l=dmg.length();
        String cc=PrismConfig.damageCode+"777aaaeeeccc".substring(PrismConfig.damageCode.length());
        String res="\u00a7"+ cc.charAt(l-1);
        if(l<4){
            return res+dmg;
        }else if(l<13){
            res+=dmg.substring(0,(l-1)%3+1)+(l%3==0?"":"."+dmg.substring((l-1)%3+1,3));
            res+= new String[]{"k", "m", "b"}[(l-4)/3];
            return res;
        }else{
            return "error";
        }
    }
}
