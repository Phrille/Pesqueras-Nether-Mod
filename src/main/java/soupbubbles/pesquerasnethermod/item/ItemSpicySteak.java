package soupbubbles.pesquerasnethermod.item;

import soupbubbles.pesquerasnethermod.item.base.ItemFoodBase;
import soupbubbles.pesquerasnethermod.lib.Constants;
import soupbubbles.pesquerasnethermod.lib.Names;

public class ItemSpicySteak extends ItemFoodBase
{
	public ItemSpicySteak()
	{
		super( Names.ITEM_STEAK_SPICY, Constants.SPICY_STEAK_HEAL_AMOUNT, Constants.SPICY_STEAK_SATURATION, false);
	}
}
