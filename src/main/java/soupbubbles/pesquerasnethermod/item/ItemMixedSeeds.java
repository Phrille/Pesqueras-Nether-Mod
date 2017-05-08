package soupbubbles.pesquerasnethermod.item;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import soupbubbles.pesquerasnethermod.creativetab.CreativeTab;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.item.base.ItemBase;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.lib.Assets;

public class ItemMixedSeeds extends ItemSeeds
{
    private final String BASE_NAME = Names.ITEM_SEEDS_MIXED;

    public ItemMixedSeeds()
    {
        super(ModBlocks.BLOCK_THORN_BUSH, Blocks.FARMLAND);
        setRegistryName(BASE_NAME);
        setUnlocalizedName(BASE_NAME);
        setCreativeTab(CreativeTab.PESQUERASNETHERMOD_TAB);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return String.format(Assets.ITEM_PREFIX, Assets.ASSET_PREFIX, BASE_NAME);
    }
}
