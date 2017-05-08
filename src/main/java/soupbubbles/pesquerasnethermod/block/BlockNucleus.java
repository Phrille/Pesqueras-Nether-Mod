package soupbubbles.pesquerasnethermod.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import soupbubbles.pesquerasnethermod.block.base.BlockBase;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.init.ModItems;
import soupbubbles.pesquerasnethermod.lib.Constants;
import soupbubbles.pesquerasnethermod.lib.Names;

public class BlockNucleus extends BlockBase
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, Constants.NUCLEUS_MAX_AGE);
    private static final AxisAlignedBB[] NUCLEUS_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.44D, 0.0D, 0.44D, 0.56D, 0.125D, 0.56D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.25D, 0.625D), new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D), new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.75D, 0.875D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public BlockNucleus()
    {
        super(Names.BLOCK_NUCLEUS);
        setDefaultState(blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        setHardness(Constants.BLOCK_NUCLEUS_HARDNESS);
        setResistance(Constants.BLOCK_NUCLEUS_RESISTANCE);
        setCreativeTab(null);
        setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        checkAndDropBlock(world, pos, state);
        
        int age = ((Integer) state.getValue(AGE)).intValue();

        if (age < getMaxAge())
        {
            if (rand.nextInt(10) == 0)
            {
                state = state.withProperty(AGE, Integer.valueOf(age + 1));

                if (!world.isRemote)
                {
                    world.setBlockState(pos, state, 2);
                }
            }
        }
        else
        {
            if (rand.nextInt(10) == 0)
            {
                tryGrowExtensions(world, pos.getX(), pos.getY(), pos.getZ(), pos, state, rand);
            }
        }

        super.updateTick(world, pos, state, rand);
    }
    
    public boolean tryGrowExtensions(World world, int x, int y, int z, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            int direction = rand.nextInt(6);
            BlockPos growPos;

            switch (direction)
            {
                case 0:
                {
                    growPos = new BlockPos(x + 1, y, z);
                    break;
                }
                case 1:
                {
                    growPos = new BlockPos(x - 1, y, z);
                    break;
                }
                case 2:
                {
                    growPos = new BlockPos(x, y, z + 1);
                    break;
                }
                case 3:
                {
                    growPos = new BlockPos(x, y, z - 1);
                    break;
                }
                case 4:
                {
                    growPos = new BlockPos(x, y + 1, z);
                    break;
                }
                case 5:
                {
                    growPos = new BlockPos(x, y - 1, z);
                    break;
                }
                default:
                {
                    return false;
                }
            }

            if (world.getBlockState(growPos).getBlock() == Blocks.AIR)
            {
                world.setBlockState(growPos, rand.nextBoolean() && direction < 4 ? ModBlocks.BLOCK_NETHERROOT.getDefaultState() : ModBlocks.BLOCK_NETHERROOT_EXTENSION.getDefaultState());
                return true;
            }
        }

        return false;
    }
    
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!canBlockStay(world, pos, state))
        {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this)
        {
            int age = ((Integer) state.getValue(AGE)).intValue();
            IBlockState soil = world.getBlockState(pos.down());

            if (age < getMaxAge())
            {
                return isBlockValidSoil(soil.getBlock());
            }
            
            return true;
        }
        
        return isBlockValidSoil(world.getBlockState(pos.down()).getBlock());
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        int age = (state.getValue(getAge())).intValue();
        ItemStack stack = player.inventory.getCurrentItem();

        if (stack.getItem() == getFertilizer() && age < getMaxAge())
        {
            if (!world.isRemote)
            {
                state = state.withProperty(getAge(), Integer.valueOf(age + 1));

                if (!player.capabilities.isCreativeMode)
                {
                    stack.shrink(1);
                }

                ModBlocks.BLOCK_THORN_BUSH.spawnGrowParticles(world, pos, 10);
            }

            if (!world.isRemote)
            {
                world.setBlockState(pos, state, 2);
            }
        }

        return true;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        int count = 1;

        if (((Integer) state.getValue(AGE)) >= getMaxAge())
        {
            count = 1 + rand.nextInt(2) + (fortune > 0 ? rand.nextInt(fortune + 1) : 0);
        }

        for (int i = 0; i < count; i++)
        {
            ret.add(getSeed());
        }

        return ret;
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        if (((Integer) state.getValue(AGE)).intValue() == getMaxAge())
        {
            return new ItemStack(ModItems.ITEM_NUCLEUS);
        }

        return getSeed();
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        super.neighborChanged(state, world, pos, block, fromPos);
        checkAndDropBlock(world, pos, state);
    }

    public boolean isBlockValidSoil(Block block)
    {
        return block == Blocks.NETHERRACK || block == Blocks.SOUL_SAND || block == Blocks.MAGMA || block == Blocks.GRAVEL;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return NUCLEUS_AABB[((Integer) state.getValue(AGE)).intValue()];
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return NUCLEUS_AABB[((Integer) state.getValue(AGE)).intValue()];
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return isFullCube(state);
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return ((Integer) state.getValue(AGE)).intValue() == getMaxAge();
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

    public ItemStack getSeed()
    {
        return new ItemStack(ModItems.ITEM_SEEDS_NETHERROOT);
    }

    public ItemStack getCrop()
    {
        return new ItemStack(this);
    }

    public Item getFertilizer()
    {
        return Items.BLAZE_POWDER;
    }

    public PropertyInteger getAge()
    {
        return AGE;
    }

    public int getMaxAge()
    {
        return Constants.NUCLEUS_MAX_AGE;
    }
}
