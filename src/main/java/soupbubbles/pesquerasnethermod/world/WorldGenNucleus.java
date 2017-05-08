package soupbubbles.pesquerasnethermod.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import soupbubbles.pesquerasnethermod.block.BlockNucleus;
import soupbubbles.pesquerasnethermod.init.ModBlocks;

public class WorldGenNucleus extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        BlockNucleus block = ModBlocks.BLOCK_NUCLEUS;

        for (int i = 0; i < 10; i++)
        {
            int age = rand.nextInt(block.getMaxAge() + 1);
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (age < block.getMaxAge())
            {
                if (world.isAirBlock(blockpos) && block.isBlockValidSoil(world.getBlockState(blockpos.down()).getBlock()))
                {
                    world.setBlockState(blockpos, block.getBlockState().getBaseState().withProperty(block.getAge(), Integer.valueOf(age)), 2);
                } 
            }
            else
            {
                if (world.isAirBlock(blockpos) && world.getBlockState(blockpos.down()).getBlock().isFullBlock(world.getBlockState(blockpos.down())))
                {
                    world.setBlockState(blockpos, block.getBlockState().getBaseState().withProperty(block.getAge(), Integer.valueOf(age)), 2);
                } 
            }
        }

        return true;
    }
}