package soupbubbles.pesquerasnethermod.init;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import soupbubbles.pesquerasnethermod.block.base.BlockFluidFlowing;
import soupbubbles.pesquerasnethermod.block.base.BlockFluidStatic;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.util.FluidBase;
import soupbubbles.pesquerasnethermod.util.ResourceLocationHelper;

public class ModFluids
{
    public static final Fluid HONEY;
    public static final Fluid WATER_VAPOR;

    public static final Set<Fluid> FLUIDS = new HashSet<>();
    public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();

    static
    {
        HONEY = createFluid(Names.FLUID_HONEY, true, fluid -> fluid.setDensity(2000).setViscosity(4000), fluid -> new BlockFluidFlowing(fluid, new MaterialLiquid(MapColor.GOLD)));
        WATER_VAPOR = createFluid(Names.FLUID_WATER_VAPOR, false, fluid -> fluid.setDensity(-200).setTemperature(500).setViscosity(1000).setGaseous(true), fluid -> new BlockFluidStatic(fluid, new MaterialLiquid(MapColor.AIR)));
    }

    public static void registerFluids()
    {
    }

    public static void registerFluidContainers()
    {
        for (Fluid fluid : FLUIDS)
        {
            registerBucket(fluid);
        }
    }

    private static <T extends BlockFluidBase & IFluidBlock> Fluid createFluid(String name, boolean hasFlowIcon, Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory)
    {
        ResourceLocation still = ResourceLocationHelper.getFluidTexture(name, false);
        ResourceLocation flowing = ResourceLocationHelper.getFluidTexture(name, hasFlowIcon);

        Fluid fluid = new FluidBase(name, still, flowing);
        boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

        if (useOwnFluid)
        {
            fluidPropertyApplier.accept(fluid);
            registerFluidBlock(blockFactory.apply(fluid));
        }
        else
        {
            fluid = FluidRegistry.getFluid(name);
        }

        FLUIDS.add(fluid);

        return fluid;
    }

    private static <T extends BlockFluidBase & IFluidBlock> T registerFluidBlock(T block)
    {
        ModBlocks.registerBlock(block);
        MOD_FLUID_BLOCKS.add(block);

        return block;
    }

    private static void registerBucket(Fluid fluid)
    {
        FluidRegistry.addBucketForFluid(fluid);
    }
}
