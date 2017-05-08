package soupbubbles.pesquerasnethermod.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import soupbubbles.pesquerasnethermod.util.ResourceLocationHelper;

public class RenderGenericMob extends RenderLiving
{
    public RenderGenericMob(RenderManager renderManager, ModelBase model, float shadowSize)
    {
        super(renderManager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return ResourceLocationHelper.getEntityTexture(entity.getName());
    }
}
