package soupbubbles.pesquerasnethermod.handler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import soupbubbles.pesquerasnethermod.init.ModFluids;
import soupbubbles.pesquerasnethermod.init.ModItems;

public class PlayerEventHandler
{
    public PlayerEventHandler()
    {
        MinecraftForge.EVENT_BUS.register(new GlassBottleEvent());
    }

    @SubscribeEvent
    public void rightClickEntity(PlayerInteractEvent.EntityInteract event)
    {
        if (event.getTarget() instanceof EntityWolf && event.getItemStack() != null && event.getItemStack().getItem() == ModItems.ITEM_STEAK_SPICY)
        {
            EntityWolf wolf = (EntityWolf) event.getTarget();

            if (!event.getWorld().isRemote)
            {
                wolf.setTamed(false);

                if (!event.getEntityPlayer().capabilities.isCreativeMode)
                {
                    wolf.setAttackTarget(event.getEntityPlayer());
                    wolf.setAngry(true);
                    event.getItemStack().shrink(1);
                }
            }

            for (int i = 0; i < 7; ++i)
            {
                double d0 = event.getWorld().rand.nextGaussian() * 0.02D;
                double d1 = event.getWorld().rand.nextGaussian() * 0.02D;
                double d2 = event.getWorld().rand.nextGaussian() * 0.02D;

                event.getWorld().spawnParticle(EnumParticleTypes.FLAME, wolf.posX + (double) (event.getWorld().rand.nextFloat() * wolf.width * 2.0F) - (double) wolf.width, wolf.posY + 0.5D + (double) (event.getWorld().rand.nextFloat() * wolf.height), wolf.posZ + (double) (event.getWorld().rand.nextFloat() * wolf.width * 2.0F) - (double) wolf.width, d0, d1, d2, new int[0]);
            }
        }

        event.setResult(Result.ALLOW);
    }

    @SubscribeEvent
    public void rightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        if (event.getItemStack() != null && event.getItemStack().getItem() == Items.WATER_BUCKET)
        {
            if (!event.getWorld().isRemote && event.getWorld().provider.doesWaterVaporize())
            {
                if (event.getWorld().isBlockModifiable(event.getEntityPlayer(), event.getPos()))
                {
                    BlockPos targetPos = event.getPos().offset(event.getFace());

                    if (event.getEntityPlayer().canPlayerEdit(targetPos, event.getFace(), event.getItemStack()))
                    {
                        System.out.println("Spawning Water Vapor");
                        FluidUtil.tryPlaceFluid(event.getEntityPlayer(), event.getWorld(), targetPos, event.getItemStack(), new FluidStack(ModFluids.WATER_VAPOR, Fluid.BUCKET_VOLUME));
                    }
                }
            }
        }

        event.setResult(Result.ALLOW);
    }

    private class GlassBottleEvent extends ItemGlassBottle
    {
        @SubscribeEvent
        public void fillGlassBottle(PlayerInteractEvent.RightClickItem event)
        {
            RayTraceResult raytraceresult = rayTrace(event.getWorld(), event.getEntityPlayer(), true);

            if (event.getItemStack() != null && event.getItemStack().getItem() == Items.GLASS_BOTTLE)
            {
                if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
                {
                    BlockPos pos = raytraceresult.getBlockPos();

                    if (event.getWorld().getBlockState(pos).getBlock() == ModFluids.HONEY.getBlock())
                    {
                        event.getWorld().playSound(event.getEntityPlayer(), event.getEntityPlayer().posX, event.getEntityPlayer().posY, event.getEntityPlayer().posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);

                        if (!event.getWorld().isRemote)
                        {
                            event.getEntityPlayer().inventory.mainInventory.set(event.getEntityPlayer().inventory.currentItem, turnBottleIntoItem(event.getItemStack(), event.getEntityPlayer(), new ItemStack(ModItems.ITEM_BOTTLE_HONEY, 1, 0)));
                        }
                    }
                }

            }

            event.setResult(Result.ALLOW);
        }
    }
}
