package soupbubbles.pesquerasnethermod.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.init.ModFluids;

public class WorldGenBeehive extends WorldGenerator
{
    private static final int[][] STATIC_BLOCK_POS = new int[][] {{3, 0, 3}, {1, 1, 2}, {1, 1, 3}, {1, 1, 4}, {2, 1, 1}, {3, 1, 1}, {4, 1, 1}, {5, 1, 2}, {5, 1, 4}, {5, 1, 3}, {2, 1, 5}, {3, 1, 5}, {4, 1, 5}, {1, 2, 1}, {1, 7, 1}, {1, 2, 5}, {1, 7, 5}, {5, 2, 1}, {5, 7, 1}, {5, 2, 5}, {5, 7, 5}, {0, 3, 3}, {0, 4, 3}, {0, 5, 3}, {0, 6, 3}, {3, 3, 0}, {3, 4, 0}, {3, 5, 0}, {3, 6, 0}, {6, 3, 3}, {6, 4, 3}, {6, 5, 3}, {6, 6, 3}, {3, 3, 6}, {3, 4, 6}, {3, 5, 6}, {3, 6, 6}, {1, 8, 2}, {1, 8, 3}, {1, 8, 4}, {2, 8, 1}, {3, 8, 1}, {4, 8, 1}, {5, 8, 2}, {5, 8, 4}, {5, 8, 3}, {2, 8, 5}, {3, 8, 5}, {4, 8, 5}, {3, 9, 3}};
    private static final BlockPos[] POS = new BlockPos[STATIC_BLOCK_POS.length];

    public WorldGenBeehive()
    {
        for (int i = 0; i < STATIC_BLOCK_POS.length; i++)
        {
            POS[i] = new BlockPos(STATIC_BLOCK_POS[i][0], STATIC_BLOCK_POS[i][1], STATIC_BLOCK_POS[i][2]);
        }
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {

        if (world.getBlockState(pos.up()).getBlock() != Blocks.NETHERRACK && world.getBlockState(pos.up()).getBlock() != Blocks.SOUL_SAND)
        {
            return false;
        }

        BlockPos position = new BlockPos(pos.getX() - 3, pos.getY() - 9, pos.getZ() - 3);

        if (canSpawn(world, position))
        {
            generateExterior(world, position, rand);
            generateInterior(world, position, rand);
        }

        return false;
    }

    public void generateExterior(World world, BlockPos pos, Random rand)
    {
        for (int x = 0; x < 7; x++)
        {
            for (int z = 0; z < 7; z++)
            {
                for (int y = 2; y < 8; y++)
                {
                    BlockPos position = pos.add(x, y, z);
                    world.setBlockState(position, ModBlocks.BLOCK_HARD_WAX.getDefaultState());
                }
            }
        }

        for (int x = 1; x < 6; x++)
        {
            for (int z = 1; z < 6; z++)
            {
                for (int y = 1; y < 9; y++)
                {
                    BlockPos position = pos.add(x, y, z);

                    if (world.getBlockState(position).getBlock() == ModBlocks.BLOCK_HARD_WAX)
                    {
                        world.setBlockToAir(position);
                    }
                    else
                    {
                        world.setBlockState(position, ModBlocks.BLOCK_HARD_WAX.getDefaultState());
                    }
                }
            }
        }

        for (int x = 2; x < 5; x++)
        {
            for (int z = 2; z < 5; z++)
            {
                BlockPos position = pos.add(x, 0, z);
                world.setBlockState(position, ModBlocks.BLOCK_HARD_WAX.getDefaultState());
            }
        }

        for (int x = 2; x < 5; x++)
        {
            for (int z = 2; z < 5; z++)
            {
                BlockPos position = pos.add(x, 9, z);
                world.setBlockState(position, ModBlocks.BLOCK_HARD_WAX.getDefaultState());
            }
        }

        pillar(1, 1, pos, world, ModBlocks.BLOCK_HARD_WAX);
        pillar(5, 1, pos, world, ModBlocks.BLOCK_HARD_WAX);
        pillar(1, 5, pos, world, ModBlocks.BLOCK_HARD_WAX);
        pillar(5, 5, pos, world, ModBlocks.BLOCK_HARD_WAX);

        pillar(0, 0, pos, world);
        pillar(1, 0, pos, world);
        pillar(0, 1, pos, world);

        pillar(6, 0, pos, world);
        pillar(6, 1, pos, world);
        pillar(5, 0, pos, world);

        pillar(0, 6, pos, world);
        pillar(1, 6, pos, world);
        pillar(0, 5, pos, world);

        pillar(6, 6, pos, world);
        pillar(6, 5, pos, world);
        pillar(5, 6, pos, world);

        block(1, 1, 1, pos, world);
        block(1, 1, 5, pos, world);
        block(5, 1, 1, pos, world);
        block(5, 1, 5, pos, world);

        block(1, 8, 1, pos, world);
        block(1, 8, 5, pos, world);
        block(5, 8, 1, pos, world);
        block(5, 8, 5, pos, world);

        for (int x = 0; x < 7; x++)
        {
            for (int z = 0; z < 7; z++)
            {
                for (int y = 0; y < 10; y++)
                {
                    BlockPos position = pos.add(x, y, z);

                    if (world.getBlockState(position).getBlock() == ModBlocks.BLOCK_HARD_WAX && rand.nextInt(4) == 0)
                    {
                        world.setBlockToAir(position);
                    }
                }
            }
        }

        for (int x = 0; x < 7; x++)
        {
            for (int z = 0; z < 7; z++)
            {
                for (int y = 0; y < 10; y++)
                {
                    BlockPos position = pos.add(x, y, z);

                    for (int i = 0; i < POS.length; i++)
                    {
                        if (x == POS[i].getX() && y == POS[i].getY() && z == POS[i].getZ())
                        {
                            world.setBlockState(position, ModBlocks.BLOCK_HARD_WAX.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    public void generateInterior(World world, BlockPos pos, Random rand)
    {
        pillar(1, 2, pos, world, rand);
        pillar(1, 3, pos, world, rand, true);
        pillar(1, 4, pos, world, rand);

        pillar(2, 1, pos, world, rand);
        pillar(3, 1, pos, world, rand, true);
        pillar(4, 1, pos, world, rand);

        pillar(2, 5, pos, world, rand);
        pillar(3, 5, pos, world, rand, true);
        pillar(4, 5, pos, world, rand);

        pillar(5, 2, pos, world, rand);
        pillar(5, 3, pos, world, rand, true);
        pillar(5, 4, pos, world, rand);

        for (int x = 2; x < 5; x++)
        {
            for (int z = 2; z < 5; z++)
            {
                BlockPos position = pos.add(x, 2, z);

                if (rand.nextInt(5) == 0)
                {
                    world.setBlockState(position, ModBlocks.BLOCK_HARD_WAX.getDefaultState());
                }
                else
                {
                    world.setBlockState(position, ModBlocks.BLOCK_WAX.getDefaultState());
                }

                if (rand.nextInt(10) == 0)
                {
                    world.setBlockState(position, ModFluids.HONEY.getBlock().getDefaultState());
                    world.scheduleBlockUpdate(position, ModFluids.HONEY.getBlock(), 1, 2);
                }
            }
        }

        for (int x = 2; x < 5; x++)
        {
            for (int z = 2; z < 5; z++)
            {
                BlockPos position = pos.add(x, 8, z);

                if (rand.nextInt(5) == 0)
                {
                    world.setBlockState(position, ModBlocks.BLOCK_HARD_WAX.getDefaultState());
                }
                else
                {
                    world.setBlockState(position, ModBlocks.BLOCK_WAX.getDefaultState());
                }

                if (rand.nextInt(10) == 0)
                {
                    world.setBlockState(position, ModFluids.HONEY.getBlock().getDefaultState());
                    world.scheduleBlockUpdate(position, ModFluids.HONEY.getBlock(), 1, 2);
                }
            }
        }
        
        block(3, 5, 3, pos, world, ModBlocks.BLOCK_HEART);
    }

    public boolean canSpawn(World world, BlockPos pos)
    {
        for (int x = 0; x < 7; x++)
        {
            for (int z = 0; z < 7; z++)
            {
                for (int y = 2; y < 8; y++)
                {
                    BlockPos position = pos.add(x, y, z);

                    if (world.getBlockState(position).getBlock() != Blocks.AIR)
                    {
                        return false;
                    }
                }
            }
        }

        for (int x = 1; x < 6; x++)
        {
            for (int z = 1; z < 6; z++)
            {
                for (int y = 0; y < 10; y++)
                {
                    BlockPos position = pos.add(x, y, z);

                    if (world.getBlockState(position).getBlock() != Blocks.AIR)
                    {
                        return false;
                    }
                }
            }

        }

        return true;
    }

    private void block(int x, int y, int z, BlockPos pos, World world)
    {
        block(x, y, z, pos, world, Blocks.AIR);
    }

    private void block(int x, int y, int z, BlockPos pos, World world, Block block)
    {
        block(x, y, z, pos, world, block.getDefaultState());
    }
    
    private void block(int x, int y, int z, BlockPos pos, World world, IBlockState state)
    {
        BlockPos position = pos.add(x, y, z);
        world.setBlockState(position, state);
    }

    private void pillar(int x, int z, BlockPos pos, World world)
    {
        pillar(x, z, pos, world, Blocks.AIR);
    }

    private void pillar(int x, int z, BlockPos pos, World world, Block block)
    {
        for (int y = 2; y < 8; y++)
        {
            BlockPos position = pos.add(x, y, z);
            world.setBlockState(position, block.getDefaultState());
        }
    }

    private void pillar(int x, int z, BlockPos pos, World world, Random rand)
    {
        pillar(x, z, pos, world, rand, false);
    }

    private void pillar(int x, int z, BlockPos pos, World world, Random rand, boolean honey)
    {
        for (int y = 2; y < 8; y++)
        {
            BlockPos position = pos.add(x, y, z);

            if (rand.nextInt(5) == 0)
            {
                world.setBlockState(position, ModBlocks.BLOCK_HARD_WAX.getDefaultState());
            }
            else
            {
                world.setBlockState(position, ModBlocks.BLOCK_WAX.getDefaultState());
            }

            if (honey && rand.nextInt(20) == 0)
            {
                world.setBlockState(position, ModFluids.HONEY.getBlock().getDefaultState());
                world.scheduleBlockUpdate(position, ModFluids.HONEY.getBlock(), 1, 2);
            }
        }
    }
}
