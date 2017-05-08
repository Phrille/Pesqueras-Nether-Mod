package soupbubbles.pesquerasnethermod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import soupbubbles.pesquerasnethermod.PesquerasNetherMod;
import soupbubbles.pesquerasnethermod.client.render.entity.RenderBee;
import soupbubbles.pesquerasnethermod.client.render.entity.RenderGenericItem;
import soupbubbles.pesquerasnethermod.client.render.entity.RenderLarva;
import soupbubbles.pesquerasnethermod.entity.EntityBee;
import soupbubbles.pesquerasnethermod.entity.EntityHellfireBottle;
import soupbubbles.pesquerasnethermod.entity.EntityLarva;
import soupbubbles.pesquerasnethermod.lib.Constants;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.lib.Reference;

public class ModEntities
{
    public static void initEntities()
    {
        //registerEntity(EntityBee.class, Names.ENTITY_BEE, 0, 80, 3, true, Constants.ENTITY_BEE_COLOR_PRIMARY, Constants.ENTITY_BEE_COLOR_SECONDARY);
        registerEntity(EntityLarva.class, Names.ENTITY_LARVA, 1, 80, 3, true, Constants.ENTITY_LARVA_COLOR_PRIMARY, Constants.ENTITY_LARVA_COLOR_SECONDARY);
        //registerEntity(EntityHellfire.class, Names.ENTITY_HELLFIRE, 2, 80, 3, true, Constants.COLOR_ENTITY_HELLFIRE_PRIMARY, Constants.COLOR_ENTITY_HELLFIRE_SECONDARY);

        registerEntity(EntityHellfireBottle.class, Names.ENTITY_BOTTLE_SPLASH_HELLFIRE, 3, 80, 3, true);

    }

    public static void registerEntityRenderers()
    {
        //RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, renderManager -> new RenderBee(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityLarva.class, renderManager -> new RenderLarva(renderManager));
        
        //RenderingRegistry.registerEntityRenderingHandler(EntityHellfire.class, renderManager -> new RenderHellfire(renderManager));
  
        RenderingRegistry.registerEntityRenderingHandler(EntityHellfireBottle.class, renderManager -> new RenderGenericItem(renderManager, ModItems.ITEM_BOTTLE_HELLFIRE, 1, Minecraft.getMinecraft().getRenderItem()));
    }

    private static void registerEntity(Class<? extends Entity> clazz, String name, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), clazz, Reference.MOD_ID + "." + name, id, PesquerasNetherMod.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    private static void registerEntity(Class<? extends Entity> clazz, String name, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int primaryColor, int secondaryColor)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), clazz, Reference.MOD_ID + "." + name, id, PesquerasNetherMod.instance, trackingRange, updateFrequency, sendsVelocityUpdates, primaryColor, secondaryColor);
    }
}