package soupbubbles.pesquerasnethermod.block.base;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import soupbubbles.pesquerasnethermod.lib.Assets;

public abstract class BlockBushBase extends BlockBush
{
    private final String BASE_NAME;

    public BlockBushBase(String name)
    {
        BASE_NAME = name;
        setRegistryName(BASE_NAME);
        setUnlocalizedName(BASE_NAME);
        setCreativeTab(null);
        setSoundType(SoundType.PLANT);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format(Assets.BLOCK_PREFIX, Assets.ASSET_PREFIX, BASE_NAME);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        int age = ((Integer) state.getValue(getAge())).intValue();

        ItemStack stack = player.inventory.getCurrentItem();

        if (stack.getItem() == getFertilizer() && age < getMaxAge())
        {
            age = age + world.rand.nextInt(1) + 1;
            state = state.withProperty(getAge(), Integer.valueOf(age > getMaxAge() ? getMaxAge() : age));
            
            if (!world.isRemote && !player.capabilities.isCreativeMode)
            {
                stack.shrink(1);
            }

            spawnGrowParticles(world, pos, 10);

            return grow(world, pos, state);
        }

        return true;
    }

    public boolean grow(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            world.setBlockState(pos, state, 2);
            ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));

            return true;
        }

        return true;
    }

    // Doesn't work if the block has no collision
    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance)
    {
        if (!world.isRemote)
        {
            dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }

        super.onFallenUpon(world, pos, entity, fallDistance);
    }

    @SideOnly(Side.CLIENT)
    public static void spawnGrowParticles(World world, BlockPos pos, int amount)
    {
        if (amount == 0)
        {
            amount = 15;
        }

        IBlockState iblockstate = world.getBlockState(pos);

        if (iblockstate.getMaterial() != Material.AIR)
        {
            for (int i = 0; i < amount; ++i)
            {
                double d0 = world.rand.nextGaussian() * 0.02D;
                double d1 = world.rand.nextGaussian() * 0.02D;
                double d2 = world.rand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.FLAME, (double) ((float) pos.getX() + world.rand.nextFloat()), (double) pos.getY() + (double) world.rand.nextFloat() * iblockstate.getBoundingBox(world, pos).maxY, (double) ((float) pos.getZ() + world.rand.nextFloat()), d0, d1, d2, new int[0]);
            }
        }
        else
        {
            for (int i1 = 0; i1 < amount; ++i1)
            {
                double d0 = world.rand.nextGaussian() * 0.02D;
                double d1 = world.rand.nextGaussian() * 0.02D;
                double d2 = world.rand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.FLAME, (double) ((float) pos.getX() + world.rand.nextFloat()), (double) pos.getY() + (double) world.rand.nextFloat() * 1.0f, (double) ((float) pos.getZ() + world.rand.nextFloat()), d0, d1, d2, new int[0]);
            }
        }
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return getSeed();
    }

    public abstract ItemStack getSeed();

    public abstract ItemStack getCrop();

    public abstract Item getFertilizer();

    public abstract PropertyInteger getAge();

    public abstract int getMaxAge();
}
