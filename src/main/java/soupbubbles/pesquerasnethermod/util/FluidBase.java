package soupbubbles.pesquerasnethermod.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import soupbubbles.pesquerasnethermod.lib.Reference;
import soupbubbles.pesquerasnethermod.lib.Assets;

public class FluidBase extends Fluid
{
    public FluidBase(String name, ResourceLocation still, ResourceLocation flowing)
    {
        super(name, still, flowing);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "fluid." + Reference.MOD_ID + "." + fluidName + ".name";
    }
}
