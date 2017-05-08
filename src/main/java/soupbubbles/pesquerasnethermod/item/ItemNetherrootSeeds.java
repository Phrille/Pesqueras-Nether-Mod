package soupbubbles.pesquerasnethermod.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import soupbubbles.pesquerasnethermod.block.BlockNucleus;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.item.base.ItemBase;
import soupbubbles.pesquerasnethermod.lib.Assets;
import soupbubbles.pesquerasnethermod.lib.Names;

public class ItemNetherrootSeeds extends ItemBase
{
    public ItemNetherrootSeeds()
    {
        super(Names.ITEM_SEEDS_NETHERROOT);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);
        BlockNucleus block = ModBlocks.BLOCK_NUCLEUS;
        
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && block.isBlockValidSoil(state.getBlock()) && world.isAirBlock(pos.up()))
        {
            world.setBlockState(pos.up(), block.getDefaultState());
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
}
