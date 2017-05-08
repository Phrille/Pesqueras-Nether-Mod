package soupbubbles.pesquerasnethermod.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes
{

    public static void init()
    {
        initModRecipes();
        initBrewingRecipes();
    }

    private static void initModRecipes()
    {
    	GameRegistry.addShapedRecipe(new ItemStack(ModItems.ITEM_ARMOR_SHELL_HELMET), "xxx", "x x", 'x', new ItemStack(ModItems.ITEM_MISC, 1, 0));
    	GameRegistry.addShapedRecipe(new ItemStack(ModItems.ITEM_ARMOR_SHELL_CHESTPLATE), "x x", "xxx", "xxx", 'x', new ItemStack(ModItems.ITEM_MISC, 1, 0));
    	GameRegistry.addShapedRecipe(new ItemStack(ModItems.ITEM_ARMOR_SHELL_LEGGINGS), "xxx", "x x", "x x", 'x', new ItemStack(ModItems.ITEM_MISC, 1, 0));
    	GameRegistry.addShapedRecipe(new ItemStack(ModItems.ITEM_ARMOR_SHELL_BOOTS), "x x", "x x", 'x', new ItemStack(ModItems.ITEM_MISC, 1, 0));

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ITEM_SEEDS_MIXED, 1, 0), new ItemStack(ModItems.ITEM_SEEDS_NETHERROOT, 1, 0), new ItemStack(Items.WHEAT_SEEDS, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ITEM_STEAK_SPICY, 1, 0), new ItemStack(ModItems.ITEM_SEEDS_NETHERROOT, 1, 0), new ItemStack(Items.COOKED_BEEF, 1, 0));
    }

    private static void initBrewingRecipes()
    {
        BrewingRecipeRegistry.addRecipe(new ItemStack(ModItems.ITEM_BOTTLE_HELLFIRE, 1, 0), new ItemStack(Items.GUNPOWDER, 1, 0), new ItemStack(ModItems.ITEM_BOTTLE_HELLFIRE, 1, 1));
    }
}
