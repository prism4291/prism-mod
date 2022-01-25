package prism4291.henachoko.prism_new_mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrismUtils {
    public static void drawItemStackWithText(ItemStack stack, int x, int y, String text) {
        if(stack == null)return;

        RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();

        RenderHelper.enableGUIStandardItemLighting();
        itemRender.renderItemAndEffectIntoGUI(stack, x, y);
        itemRender.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, stack, x, y, text);
        RenderHelper.disableStandardItemLighting();
    }
    public static boolean matchDamage(String dmg){
        return damageDeleteCode(dmg).matches("\\d+");
    }
    public static String damageDeleteCode(String dmg){
    //✧|☄|❤|♞|
    dmg=dmg.replaceAll("\u00a7.","");
    return dmg.replaceAll("[\u2727\u2604\u2764\u265e]","");
    }
    public static String damageCompactor(String dmg){
        dmg=damageDeleteCode(dmg);
        int l=dmg.length();
        if(l<1){
            return "error";
        }else if(l<4){
            return "\u00a7f"+dmg;
        }else if(l<13){
            String res="\u00a7f"+dmg.substring(0,(l-1)%3+1)+l%3==0?"":"."+dmg.substring((l-1)%3+1,3);
            res+= new String[]{"k", "m", "b"}[(numList.size()-4)/3];
            return res;
        }else{
            return "error";
        }
    }
}
