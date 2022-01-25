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
        dmg=dmg.replaceAll("\u00a7k|\u00a7l|\u00a7m|\u00a7n|\u00a7o|\u00a7r","");
        dmg=dmg.replaceAll("\u00a7.\u2727|\u00a7.\u2604|\u00a7.\u2764|\u00a7.\u265e","");
        dmg=dmg.replaceAll("[\u2727\u2604\u2764\u265e]","");
        //✧|☄|❤|♞|
        return dmg.matches("((\u00a7.)*\\d)+(\u00a7.)*");
    }
    public static String damageCompactor(String dmg){
        List<String> numData= Arrays.asList(dmg.split("((\u00a7.)*\\d)"));
        List<String> numList=new ArrayList<>();
        for (String num : numData) {
            if (num.matches(".*\\d")) {
                numList.add(num.substring(num.length() - 1));
            }
        }
        if(numList.size()<1){
            return "error";
        }else if(numList.size()<4){
            return String.join("",numList);
        }else if(numList.size()<13){
            String res="\u00a7f"+String.join("",numList.subList(0,(numList.size()-1)%3+1))+(numList.size()%3==0?"":"."+String.join("",numList.subList((numList.size()-1)%3+1,3)));
            res+= new String[]{"k", "m", "b"}[(numList.size()-4)/3];
            return res;
        }else{
            return "error";
        }
    }
}
