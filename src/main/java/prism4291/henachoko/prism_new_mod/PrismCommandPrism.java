package prism4291.henachoko.prism_new_mod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class PrismCommandPrism extends CommandBase {

    static String usage = "/prism [enable/disable]\n/prism test [enable/disable]\n/prism inv <x> <y> <size>";


    @Override
    public String getCommandName() {
        return "prism";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return usage;
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) {
        if (strings.length == 0) {
            String msg = "Prism Mod : ";
            if (PrismConfig.modEnabled) {
                msg += "enabled";
            } else {
                msg += "disabled";
            }
            msg += "\n" + usage;
            iCommandSender.addChatMessage(new ChatComponentText(msg));
        } else {
            switch (strings[0]) {
                case "enable": {
                    if (strings.length == 1) {
                        PrismConfig.modEnable();
                        String msg = "Enable Prism Mod";
                        iCommandSender.addChatMessage(new ChatComponentText(msg));
                    } else {
                        iCommandSender.addChatMessage(new ChatComponentText(usage));
                    }
                    break;
                }
                case "disable": {
                    if (strings.length == 1) {
                        PrismConfig.modDisable();
                        String msg = "Disable Prism Mod";
                        iCommandSender.addChatMessage(new ChatComponentText(msg));
                    } else {
                        iCommandSender.addChatMessage(new ChatComponentText(usage));
                    }
                    break;
                }
                case "test": {
                    if (strings.length == 1) {
                        String msg = "Prism Mod Test : ";
                        if (PrismConfig.testEnabled) {
                            msg += "enabled";
                        } else {
                            msg += "disabled";
                        }
                        iCommandSender.addChatMessage(new ChatComponentText(msg));
                    } else if (strings.length == 2) {
                        if (strings[1].equals("enable")) {
                            PrismConfig.testEnable();
                            String msg = "Enable Prism Mod";
                            iCommandSender.addChatMessage(new ChatComponentText(msg));
                        } else if (strings[1].equals("disable")) {
                            PrismConfig.testDisable();
                            String msg = "Disable Prism Mod";
                            iCommandSender.addChatMessage(new ChatComponentText(msg));
                        } else {
                            iCommandSender.addChatMessage(new ChatComponentText(usage));
                        }
                    } else {
                        iCommandSender.addChatMessage(new ChatComponentText(usage));
                    }

                    break;
                }
                case "inv": {
                    if (strings.length == 1) {
                        String msg = "Prism Mod Inventory : ";
                        msg += PrismConfig.invX + PrismConfig.invY + PrismConfig.invSize;
                        iCommandSender.addChatMessage(new ChatComponentText(msg));
                    } else if (strings.length == 4) {
                        PrismConfig.invChange(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]), Double.parseDouble(strings[3]));
                        String msg = "Changed inv";
                        iCommandSender.addChatMessage(new ChatComponentText(msg));
                    } else {
                        iCommandSender.addChatMessage(new ChatComponentText(usage));
                    }
                    break;
                }
                /*
                case "gui": {
                    if (strings.length == 1) {

                        String msg = "Added Gui";
                        iCommandSender.addChatMessage(new ChatComponentText(msg));
                    }else if (strings.length == 2) {
                        if(strings[1].equals("delete")){
                            String msg = "";

                                msg+="Empty";

                            iCommandSender.addChatMessage(new ChatComponentText(msg));
                        }else{
                            iCommandSender.addChatMessage(new ChatComponentText(usage));
                        }
                    }else if (strings.length == 3) {
                                                String msg = "Added Gui";
                        iCommandSender.addChatMessage(new ChatComponentText(msg));
                    }else{
                        iCommandSender.addChatMessage(new ChatComponentText(usage));
                    }
                }*/
                default:
                    iCommandSender.addChatMessage(new ChatComponentText(usage));
                    break;
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

}
