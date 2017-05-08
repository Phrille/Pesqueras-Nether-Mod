package soupbubbles.pesquerasnethermod.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import soupbubbles.pesquerasnethermod.block.BlockNucleus;
import soupbubbles.pesquerasnethermod.creativetab.CreativeTab;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.item.base.ItemBase;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.world.WorldGenBeehive;

public class ItemNucleus extends ItemBase
{
    public ItemNucleus()
    {
        super(Names.ITEM_BLOCK_NUCLEUS);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);
        BlockNucleus block = ModBlocks.BLOCK_NUCLEUS;

        if (player.canPlayerEdit(pos.offset(facing), facing, stack))
        {
            world.setBlockState(pos.offset(facing), block.getBlockState().getBaseState().withProperty(block.getAge(), Integer.valueOf(block.getMaxAge())), 2);
            stack.shrink(1);
            world.playSound(player, pos.offset(facing), SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
}