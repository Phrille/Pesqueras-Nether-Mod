package soupbubbles.pesquerasnethermod.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.util.EnumHelper;
import soupbubbles.pesquerasnethermod.item.base.ItemArmorBase;
import soupbubbles.pesquerasnethermod.lib.Assets;
import soupbubbles.pesquerasnethermod.lib.Reference;

public class Utils
{
    public static void sendMessage(EntityPlayer player, String name)
    {
        player.sendMessage(new TextComponentTranslation(getMessage(name), new Object[0]));
    }
    
    public static void sendStatusMessage(EntityPlayer player, String name)
    {
        player.sendStatusMessage(new TextComponentTranslation(getMessage(name), new Object[0]), true);
    }
    
    private static String getMessage(String name)
    {
        return Assets.CHAT_MESSAGE_PREFIX + "." + Reference.MOD_ID + "." + name;
    }
    
    public static ArmorMaterial addArmorMaterial(String name, int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness)
    {
    	return EnumHelper.addArmorMaterial(name, Assets.TEXTURE_PREFIX + name, durability, reductionAmounts, enchantability, soundOnEquip, toughness);
    }
    
    public static String getArmorName(ItemArmorBase itemArmor)
    {
    	return itemArmor.getArmorMaterial().getName();
    }
}
