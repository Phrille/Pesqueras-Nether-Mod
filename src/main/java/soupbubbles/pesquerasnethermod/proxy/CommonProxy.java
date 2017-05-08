package soupbubbles.pesquerasnethermod.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import soupbubbles.pesquerasnethermod.handler.BlockEventHandler;
import soupbubbles.pesquerasnethermod.handler.ConfigurationHandler;
import soupbubbles.pesquerasnethermod.handler.FuelHandler;
import soupbubbles.pesquerasnethermod.handler.LivingEventHandler;
import soupbubbles.pesquerasnethermod.handler.PlayerEventHandler;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.init.ModEntities;
import soupbubbles.pesquerasnethermod.init.ModFluids;
import soupbubbles.pesquerasnethermod.init.ModItems;
import soupbubbles.pesquerasnethermod.init.ModRecipes;
import soupbubbles.pesquerasnethermod.world.ModWorldGenerator;

public abstract class CommonProxy implements IProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
  
        GameRegistry.registerWorldGenerator(new ModWorldGenerator(), 0);
        
        ModFluids.registerFluids();
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModFluids.registerFluidContainers();
        ModBlocks.registerTileEntities();
        ModEntities.initEntities();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new LivingEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        //MinecraftForge.EVENT_BUS.register(new BlockEventHandler());

        ModRecipes.init();
        GameRegistry.registerFuelHandler(new FuelHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
