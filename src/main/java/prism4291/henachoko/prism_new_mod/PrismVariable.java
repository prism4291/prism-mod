package prism4291.henachoko.prism_new_mod;

import java.util.*;


import net.minecraft.entity.item.EntityArmorStand;

public class PrismVariable {
    public static List<UUID> uuidList=new ArrayList<>();
    public static List<damageIndicator> armorStandList=new ArrayList<>();
    public static float ticks=0;
    public static int t=0;
    public static Random random=new Random();
    static class damageIndicator{
        double indicatorX;
        double indicatorY;
        double indicatorZ;
        long time;
        long lastTime;
        String text;
        damageIndicator(EntityArmorStand armorStand){
            indicatorX=armorStand.posX+(random.nextDouble()-0.5);
            indicatorY=armorStand.posY;
            indicatorZ=armorStand.posZ+(random.nextDouble()-0.5);
            time=System.currentTimeMillis();
            lastTime=System.currentTimeMillis();
            text=PrismUtils.damageCompactor(armorStand.getCustomNameTag());
        }
    }
}
