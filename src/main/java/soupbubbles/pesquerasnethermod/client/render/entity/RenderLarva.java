package soupbubbles.pesquerasnethermod.client.render.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import soupbubbles.pesquerasnethermod.client.model.ModelLarva;

public class RenderLarva extends RenderGenericMob
{
    public RenderLarva(RenderManager renderManager)
    {
        super(renderManager, new ModelLarva(), 0.2F);
    }

}
