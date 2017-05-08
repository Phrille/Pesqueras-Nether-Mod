package soupbubbles.pesquerasnethermod.util;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import soupbubbles.pesquerasnethermod.lib.Assets;

public class ResourceLocationHelper
{
    public static ResourceLocation getEntityTexture(String name)
    {
        return new ResourceLocation(Assets.ASSET_PREFIX, Assets.ENTITY_TEXTURES_DIR + name + ".png");
    }
    
    public static ResourceLocation getFluidTexture(String name, boolean flowing)
    {
        return new ResourceLocation(Assets.FLUID_TEXTURES_DIR + "fluid_" + name + (flowing ? "_flowing" : "_still"));
    }
}
