package soupbubbles.pesquerasnethermod.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import soupbubbles.pesquerasnethermod.init.ModBlocks;

public class LivingEventHandler
{

    @SubscribeEvent
    public void onLivingJumped(LivingEvent.LivingJumpEvent event)
    {
        if (!event.getEntityLiving().getEntityWorld().isRemote && event.getEntityLiving() instanceof EntityPlayer)
        {
            World world = event.getEntityLiving().getEntityWorld();
            int x = (int) event.getEntityLiving().posX;
            int y = (int) event.getEntityLiving().posY;
            int z = (int) event.getEntityLiving().posZ;

            if (world.getBlockState(new BlockPos(x, y -1, z)).getBlock() == ModBlocks.BLOCK_WAX)
            {
            }
        }
    }
}
