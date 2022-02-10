package prism4291.henachoko.prism_new_mod;

import java.util.*;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;

public class PrismVariable {
    public static List<UUID> uuidList=new ArrayList<>();
    public static int uuidDeleteLength=0;
    public static List<damageIndicator> armorStandList=new ArrayList<>();
    public static int armorStandDeleteLength=0;
    public static float ticks=0;
    public static int t=0;
    public static Random random=new Random();
    public static long currentTime=System.currentTimeMillis();
    public static long lastCurrentTime=System.currentTimeMillis();

    static class damageIndicator{
        double indicatorX;
        double indicatorY;
        double indicatorZ;
        long time;
        String text;
        damageIndicator(EntityLivingBase entity){
            indicatorX=entity.posX+(random.nextDouble()-0.5);
            indicatorY=entity.posY;
            indicatorZ=entity.posZ+(random.nextDouble()-0.5);
            time=System.currentTimeMillis();
            text=PrismUtils.damageCompactor(entity.getCustomNameTag());
        }
    }
    static double groundY=0;
}
