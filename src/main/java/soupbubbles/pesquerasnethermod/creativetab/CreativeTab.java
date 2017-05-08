package soupbubbles.pesquerasnethermod.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import soupbubbles.pesquerasnethermod.init.ModItems;
import soupbubbles.pesquerasnethermod.lib.Reference;

public class CreativeTab extends CreativeTabs
{
    public static final CreativeTabs PESQUERASNETHERMOD_TAB = new CreativeTab(Reference.MOD_ID);

    public CreativeTab(String label)
    {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.ITEM_BOTTLE_HONEY);
    }
}