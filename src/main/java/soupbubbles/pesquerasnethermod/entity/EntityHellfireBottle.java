package soupbubbles.pesquerasnethermod.entity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import soupbubbles.pesquerasnethermod.init.ModItems;
import soupbubbles.pesquerasnethermod.lib.Vanilla;

public class EntityHellfireBottle extends EntityThrowable
{
    public EntityHellfireBottle(World world)
    {
        super(world);
    }

    public EntityHellfireBottle(World world, EntityLivingBase thrower)
    {
        super(world, thrower);
    }

    public EntityHellfireBottle(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    protected float getGravityVelocity()
    {
        return 0.05F;
    }

    protected void onImpact(RayTraceResult result)
    {
        if (!world.isRemote)
        {
            IBlockState state = world.getBlockState(new BlockPos(posX, posY, posZ));

            if (state.getMaterial() == Material.WATER || state.getMaterial() == Material.LAVA)
            {
                destroyBottle(state.getMaterial() == Material.WATER ? true : false);
                return;
            }

            if (state.getBlock() instanceof BlockFluidClassic)
            {
                BlockFluidClassic blockFluid = (BlockFluidClassic) state.getBlock();

                if (!blockFluid.getFluid().isGaseous())
                {
                    // blockFluid.getFluid().vaporize(player, worldIn, pos,
                    // fluidStack);
                    destroyBottle(true);
                    return;
                }
            }

            ModItems.ITEM_BOTTLE_HELLFIRE.setFire(world, posX, posY, posZ);
        }

        destroyBottle(false);
    }

    private void destroyBottle(boolean isInLiquid)
    {
        playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK, isInLiquid ? 0.2F : 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));

        if (isInLiquid)
        {
            playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
        }

        if (!world.isRemote)
        {
            setDead();
        }
    }
}