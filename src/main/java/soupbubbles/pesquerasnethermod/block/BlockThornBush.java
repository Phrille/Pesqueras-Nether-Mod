 package soupbubbles.pesquerasnethermod.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import soupbubbles.pesquerasnethermod.block.base.BlockBushBase;
import soupbubbles.pesquerasnethermod.init.ModItems;
import soupbubbles.pesquerasnethermod.lib.Assets;
import soupbubbles.pesquerasnethermod.lib.Constants;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.util.ModDamageSource;

public class BlockThornBush extends BlockBushBase
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, Constants.THORN_BUSH_MAX_AGE);
    private static final AxisAlignedBB[] RED_WHEAT_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public BlockThornBush()
    {
        super(Names.BLOCK_THORN_BUSH);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        int age = ((Integer) state.getValue(AGE)).intValue();

        if (age < getMaxAge() && ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(10) == 0))
        {
            state = state.withProperty(AGE, Integer.valueOf(age + 1));
            grow(world, pos, state);
        }

        super.updateTick(world, pos, state, rand);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        int age = ((Integer) state.getValue(AGE)).intValue();
        float damage = Constants.DAMAGE_AMOUNT_THORN_BUSH;

        if (age == getMaxAge())
        {
            damage += 1F;
        }

        entity.attackEntityFrom(ModDamageSource.THORN_BUSH, damage);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        int count = 1;

        if (((Integer) state.getValue(AGE)) >= getMaxAge())
        {
            ret.add(getCrop());
            count = 1 + rand.nextInt(2) + (fortune > 0 ? rand.nextInt(fortune + 1) : 0);
        }

        for (int i = 0; i < count; i++)
        {
            ret.add(getSeed());
        }

        return ret;
    }
    
    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.SOUL_SAND;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Crop;
    }
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return RED_WHEAT_AABB[((Integer) state.getValue(AGE)).intValue()];
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer) state.getValue(AGE)).intValue();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }

    @Override
    public ItemStack getSeed()
    {
        return new ItemStack(ModItems.ITEM_SEEDS_MIXED);
    }

    @Override
    public ItemStack getCrop()
    {
        return new ItemStack(ModItems.ITEM_WHEAT_RED);
    }

    @Override
    public Item getFertilizer()
    {
        return Items.BLAZE_POWDER;
    }

    @Override
    public PropertyInteger getAge()
    {
        return AGE;
    }

    @Override
    public int getMaxAge()
    {
        return Constants.THORN_BUSH_MAX_AGE;
    }
}
