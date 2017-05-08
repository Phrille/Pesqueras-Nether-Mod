package soupbubbles.pesquerasnethermod.block.base;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import soupbubbles.pesquerasnethermod.lib.Assets;

public class BlockFluidFlowing extends BlockFluidClassic
{
    private final String BASE_NAME;

    public BlockFluidFlowing(Fluid fluid, Material material)
    {
        super(fluid, material);
        setRegistryName(fluid.getName());
        setUnlocalizedName(fluid.getName());
        setCreativeTab(null);

        BASE_NAME = fluid.getName();
    }

    @Override
    public Fluid getFluid()
    {
        if (stack != null && stack.getFluid() != null)
        {
            return stack.getFluid();
        }
        return null;
    }
    
    @Override
    public String getUnlocalizedName()
    {
        return String.format(Assets.BLOCK_PREFIX, Assets.ASSET_PREFIX, BASE_NAME);
    }
}