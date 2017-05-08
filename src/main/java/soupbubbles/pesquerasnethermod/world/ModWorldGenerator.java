package soupbubbles.pesquerasnethermod.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraftforge.fml.common.IWorldGenerator;
import soupbubbles.pesquerasnethermod.handler.ConfigurationHandler;

public class ModWorldGenerator implements IWorldGenerator
{

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.getDimension())
        {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateEnd(World world, Random rand, int x, int z)
    {
    }

    private void generateSurface(World world, Random rand, int x, int z)
    {
    }

    private void generateNether(World world, Random rand, int x, int z)
    {
        BlockPos pos;
        
        if (ConfigurationHandler.Settings.generateNucleus)
        {
            pos = new BlockPos(x, 0, z);
            
            for (int i = 0; i < rand.nextInt(10) + 1; i++)
            {
                new WorldGenNucleus().generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(120) + 4, rand.nextInt(16) + 8));
            }
        }
        
        
        if (ConfigurationHandler.Settings.generateBeehive)
        {
            pos = new BlockPos(x, 0, z);

            for (int i = 0; i < rand.nextInt(rand.nextInt(10) + 1); i++)
            {
                new WorldGenBeehive().generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(120) + 4, rand.nextInt(16) + 8));
            }
        }
        
        
        if(ConfigurationHandler.Settings.generateNetherWells)
        {
            pos = new BlockPos(x, 0, z);

            if (rand.nextInt(500) == 0)
            {
                new WorldGenNetherWell(rand.nextInt(5) == 0 ? true : false).generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(120) + 4, rand.nextInt(16) + 8));
            }
        }
    }
}
