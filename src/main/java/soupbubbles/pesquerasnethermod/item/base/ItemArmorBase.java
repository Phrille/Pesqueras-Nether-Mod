package soupbubbles.pesquerasnethermod.item.base;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import soupbubbles.pesquerasnethermod.creativetab.CreativeTab;
import soupbubbles.pesquerasnethermod.lib.Assets;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.lib.Reference;
import soupbubbles.pesquerasnethermod.util.Utils;

public abstract class ItemArmorBase extends ItemArmor
{
    private final String BASE_NAME;

    public ItemArmorBase(ArmorMaterial material, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(material, renderIndexIn, equipmentSlotIn);
        BASE_NAME = getArmorMaterial().getName() + Names.ARMOR_SUFFIXES[equipmentSlotIn.getIndex()];
        setRegistryName(BASE_NAME);
        setUnlocalizedName(BASE_NAME);
        setCreativeTab(CreativeTab.PESQUERASNETHERMOD_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return String.format(Assets.ITEM_PREFIX, Assets.ASSET_PREFIX, BASE_NAME.replace(Reference.MOD_ID + ":", ""));
    }
}
