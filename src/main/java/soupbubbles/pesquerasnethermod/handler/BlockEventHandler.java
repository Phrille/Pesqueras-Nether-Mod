package soupbubbles.pesquerasnethermod.handler;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import soupbubbles.pesquerasnethermod.init.ModFluids;

public class BlockEventHandler
{
    @SubscribeEvent
    public void onBlockAdded(BlockEvent.PlaceEvent event)
    {
        if (event.getState().getBlock() instanceof BlockLiquid)
        {
            checkForMixing(event.getWorld(), event.getPos(), event.getState());
        }
    }

    @SubscribeEvent
    public void neighborChanged(BlockEvent.NeighborNotifyEvent event)
    {
        if (event.getState().getBlock() instanceof BlockLiquid)
        {
            checkForMixing(event.getWorld(), event.getPos(), event.getState());
        }
    }

    public boolean checkForMixing(World world, BlockPos pos, IBlockState state)
    {
        System.out.println("hey");
        if (state.getMaterial() == Material.LAVA)
        {
            boolean flag = false;

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                if (enumfacing != EnumFacing.DOWN && world.getBlockState(pos.offset(enumfacing)).getMaterial() == Material.WATER)
                {
                    flag = true;
                    break;
                }
            }

            if (flag)
            {
                if (world.getBlockState(pos.up()) == Blocks.AIR)
                {
                    System.out.println("hey2");

                    world.setBlockState(pos.up(), ModFluids.WATER_VAPOR.getBlock().getDefaultState());
                }
            }
        }

        return false;
    }
}
