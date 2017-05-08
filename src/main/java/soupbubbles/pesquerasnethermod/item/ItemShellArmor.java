package soupbubbles.pesquerasnethermod.item;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import soupbubbles.pesquerasnethermod.item.base.ItemArmorBase;
import soupbubbles.pesquerasnethermod.lib.Assets;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.util.Utils;

public class ItemShellArmor extends ItemArmorBase 
{
	public static final ArmorMaterial SHELL_ARMOR_MATERIAL = Utils.addArmorMaterial(Names.ITEM_ARMOR_SHELL, 25, new int[] {1, 5, 3, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	
	public ItemShellArmor(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(SHELL_ARMOR_MATERIAL, renderIndexIn, equipmentSlotIn);
	}
}
