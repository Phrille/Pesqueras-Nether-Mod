package soupbubbles.pesquerasnethermod.item.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import soupbubbles.pesquerasnethermod.creativetab.CreativeTab;
import soupbubbles.pesquerasnethermod.lib.Assets;
import soupbubbles.pesquerasnethermod.lib.Names;

public abstract class ItemFoodBase extends ItemFood
{
    private final String BASE_NAME;

	public ItemFoodBase(String name, int amount, float saturation, boolean isWolfFood)
    {
		super(amount, saturation, isWolfFood);
		BASE_NAME = name;
		setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.PESQUERASNETHERMOD_TAB);
    }
	
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return String.format(Assets.ITEM_PREFIX, Assets.ASSET_PREFIX, BASE_NAME);
    }
}
