package soupbubbles.pesquerasnethermod.init;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelFluid.FluidLoader;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import soupbubbles.pesquerasnethermod.item.ItemMisc;
import soupbubbles.pesquerasnethermod.creativetab.CreativeTab;
import soupbubbles.pesquerasnethermod.item.ItemHellfireBottle;
import soupbubbles.pesquerasnethermod.item.ItemHoneyBottle;
import soupbubbles.pesquerasnethermod.item.ItemMixedSeeds;
import soupbubbles.pesquerasnethermod.item.ItemNetherrootSeeds;
import soupbubbles.pesquerasnethermod.item.ItemNucleus;
import soupbubbles.pesquerasnethermod.item.ItemRedWheat;
import soupbubbles.pesquerasnethermod.item.ItemShellArmor;
import soupbubbles.pesquerasnethermod.item.ItemSpicySteak;
import soupbubbles.pesquerasnethermod.util.FluidBase;

public class ModItems
{
    public static final Set<Item> ITEMS = new HashSet<>();

    public static final ItemMisc ITEM_MISC;
    public static final ItemHellfireBottle ITEM_BOTTLE_HELLFIRE;
    public static final ItemHoneyBottle ITEM_BOTTLE_HONEY;
    public static final ItemSpicySteak ITEM_STEAK_SPICY;
    public static final ItemNetherrootSeeds ITEM_SEEDS_NETHERROOT;
    public static final ItemMixedSeeds ITEM_SEEDS_MIXED;
    public static final ItemRedWheat ITEM_WHEAT_RED;
    public static final ItemNucleus ITEM_NUCLEUS;
    
    public static final ItemShellArmor ITEM_ARMOR_SHELL_HELMET;
    public static final ItemShellArmor ITEM_ARMOR_SHELL_CHESTPLATE;
    public static final ItemShellArmor ITEM_ARMOR_SHELL_LEGGINGS;
    public static final ItemShellArmor ITEM_ARMOR_SHELL_BOOTS;

    static
    {
        ITEM_MISC = registerItem(new ItemMisc());
        ITEM_BOTTLE_HELLFIRE = registerItem(new ItemHellfireBottle());
        ITEM_BOTTLE_HONEY = registerItem(new ItemHoneyBottle());
        ITEM_STEAK_SPICY = registerItem(new ItemSpicySteak());
        ITEM_SEEDS_NETHERROOT = registerItem(new ItemNetherrootSeeds());
        ITEM_SEEDS_MIXED = registerItem(new ItemMixedSeeds());
        ITEM_WHEAT_RED = registerItem(new ItemRedWheat());
        ITEM_NUCLEUS = registerItem(new ItemNucleus());
        
        ITEM_ARMOR_SHELL_HELMET = registerItem(new ItemShellArmor(1, EntityEquipmentSlot.HEAD));
        ITEM_ARMOR_SHELL_CHESTPLATE = registerItem(new ItemShellArmor(1, EntityEquipmentSlot.CHEST));
        ITEM_ARMOR_SHELL_LEGGINGS = registerItem(new ItemShellArmor(2, EntityEquipmentSlot.LEGS));
        ITEM_ARMOR_SHELL_BOOTS = registerItem(new ItemShellArmor(2, EntityEquipmentSlot.FEET));
    }

    public static void registerItems()
    {
        //ForgeModContainer.getInstance().universalBucket.setCreativeTab(CreativeTab.PESQUERASNETHERMOD_TAB);
    }

    private static <T extends Item> T registerItem(T item)
    {
        GameRegistry.register(item);
        ITEMS.add(item);

        return item;
    }
}
