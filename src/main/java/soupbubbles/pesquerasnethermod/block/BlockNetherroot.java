package soupbubbles.pesquerasnethermod.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import soupbubbles.pesquerasnethermod.block.base.BlockNetherrootBase;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.lib.Names;

public class BlockNetherroot extends BlockNetherrootBase
{
    public BlockNetherroot(String name)
    {
        super(name);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(world, pos, state, rand);

        if (BASE_NAME.equals(Names.BLOCK_NETHERROOT) || BASE_NAME.equals(Names.BLOCK_NETHERROOT_EXTENSION))
        {
            if (rand.nextInt(10) == 0)
            {
                tryGrowExtensions(world, pos.getX(), pos.getY(), pos.getZ(), pos, state, rand);
            }
        }
    }

    public boolean tryGrowExtensions(World world, int x, int y, int z, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            int direction = rand.nextInt(6);
            BlockPos growPos;

            switch (direction)
            {
                case 0:
                {
                    growPos = new BlockPos(x + 1, y, z);
                    break;
                }
                case 1:
                {
                    growPos = new BlockPos(x - 1, y, z);
                    break;
                }
                case 2:
                {
                    growPos = new BlockPos(x, y, z + 1);
                    break;
                }
                case 3:
                {
                    growPos = new BlockPos(x, y, z - 1);
                    break;
                }
                case 4:
                {
                    growPos = new BlockPos(x, y + 1, z);
                    break;
                }
                case 5:
                {
                    growPos = new BlockPos(x, y - 1, z);
                    break;
                }
                default:
                {
                    return false;
                }
            }
            
            
            if (world.getBlockState(growPos).getBlock() == Blocks.AIR)
            {
                if (BASE_NAME.equals(Names.BLOCK_NETHERROOT_EXTENSION))
                {
                    world.setBlockState(growPos, ModBlocks.BLOCK_NETHERROOT_END.getDefaultState());
                }
                else
                {
                    world.setBlockState(growPos, rand.nextBoolean() && direction < 4 ? ModBlocks.BLOCK_NETHERROOT_EXTENSION.getDefaultState() : ModBlocks.BLOCK_NETHERROOT_END.getDefaultState());
                }
                
                return true;
            }
        }
        
        return false;
    }

    @Override
    protected boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        for(EnumFacing facing : EnumFacing.values())
        {
            if (BASE_NAME.equals(Names.BLOCK_NETHERROOT) && world.getBlockState(pos.offset(facing)).getBlock() == ModBlocks.BLOCK_NUCLEUS)
            {
                return true;

            }
            else if (BASE_NAME.equals(Names.BLOCK_NETHERROOT_EXTENSION))
            {
                if (world.getBlockState(pos.offset(facing)).getBlock() == ModBlocks.BLOCK_NUCLEUS || world.getBlockState(pos.offset(facing)).getBlock() == ModBlocks.BLOCK_NETHERROOT)
                {
                    return true;
                }
            }
            else if (BASE_NAME.equals(Names.BLOCK_NETHERROOT_END))
            {
                if (world.getBlockState(pos.offset(facing)).getBlock() == ModBlocks.BLOCK_NETHERROOT_EXTENSION || world.getBlockState(pos.offset(facing)).getBlock() == ModBlocks.BLOCK_NETHERROOT)
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @Override
    protected boolean canConnectVertical(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        if (BASE_NAME.equals(Names.BLOCK_NETHERROOT_END))
        {
            return canBeConnectedTo(world, pos, facing);
        }
        
        return super.canBeConnectedTo(world, pos, facing);
    }


}
