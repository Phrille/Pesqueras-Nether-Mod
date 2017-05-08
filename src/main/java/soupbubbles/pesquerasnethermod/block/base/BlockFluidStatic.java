package soupbubbles.pesquerasnethermod.block.base;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidStatic extends BlockFluidFinite
{
    private final String BASE_NAME;

    public BlockFluidStatic(Fluid fluid, Material material)
    {
        super(fluid, material);
        setDefaultState(blockState.getBaseState().withProperty(LEVEL, 7));
        setRegistryName(fluid.getName());
        setUnlocalizedName(fluid.getName());
        setCreativeTab(null);

        BASE_NAME = fluid.getName();
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        boolean changed = false;
        int quantaRemaining = state.getValue(LEVEL) + 1;

        int prevRemaining = quantaRemaining;
        quantaRemaining = tryToFlowVerticallyInto(world, pos, quantaRemaining);

        if (quantaRemaining < 1)
        {
            return;
        }
        else if (quantaRemaining != prevRemaining)
        {
            changed = true;
            if (quantaRemaining == 1)
            {
                world.setBlockState(pos, state.withProperty(LEVEL, quantaRemaining - 1), 2);
                return;
            }
        }
        else if (quantaRemaining == 1)
        {
            return;
        }

        if (changed)
        {
            world.setBlockState(pos, state.withProperty(LEVEL, quantaRemaining - 1), 2);
        }
    }
}