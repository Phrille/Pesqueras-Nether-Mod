package soupbubbles.pesquerasnethermod.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import soupbubbles.pesquerasnethermod.client.model.ModelBee;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.util.ResourceLocationHelper;

public class RenderBee extends RenderGenericMob
{

    public RenderBee(RenderManager renderManager)
    {
        super(renderManager, new ModelBee(), 0.5F);
    }

}
