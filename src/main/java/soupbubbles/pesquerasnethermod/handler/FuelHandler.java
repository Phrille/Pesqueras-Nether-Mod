package soupbubbles.pesquerasnethermod.handler;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.common.IFuelHandler;
import soupbubbles.pesquerasnethermod.init.ModItems;

public class FuelHandler implements IFuelHandler
{
    private static final ArrayList<FuelStack> fuelBurnTime = new ArrayList<>();

    private static final ItemStack BOTTLE_HELLFIRE = new ItemStack(ModItems.ITEM_BOTTLE_HELLFIRE, 1, 0);
    private static final ItemStack BOTTLE_HELLFIRE_SPLASH = new ItemStack(ModItems.ITEM_BOTTLE_HELLFIRE, 1, 1);

    @Override
    public int getBurnTime(ItemStack stack)
    {
        for (int i = 0; i < fuelBurnTime.size(); i++)
        {
            if (stack.getItem() == fuelBurnTime.get(i).getStack().getItem())
            {
                return fuelBurnTime.get(i).getBurnTime();
            }
        }
        
        return 0;
    }
    
    static
    {
        fuelBurnTime.add(new FuelStack(BOTTLE_HELLFIRE, TileEntityFurnace.getItemBurnTime(new ItemStack(Items.LAVA_BUCKET))));
        fuelBurnTime.add(new FuelStack(BOTTLE_HELLFIRE_SPLASH, 200 + TileEntityFurnace.getItemBurnTime(new ItemStack(Items.LAVA_BUCKET))));
    }
    
    private static class FuelStack
    {
        private ItemStack stack;
        private int burnTime;
        
        public FuelStack(ItemStack itemStack, int burnAmount)
        {
            stack = itemStack;
            burnTime = burnAmount;
        }
        
        public ItemStack getStack()
        {
            return stack;
        }
        
        public int getBurnTime()
        {
            return burnTime;
        }
    }
}