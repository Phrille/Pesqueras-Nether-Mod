package soupbubbles.pesquerasnethermod.block.base;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import soupbubbles.pesquerasnethermod.creativetab.CreativeTab;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.init.ModItems;
import soupbubbles.pesquerasnethermod.lib.Assets;
import soupbubbles.pesquerasnethermod.lib.Constants;

public abstract class BlockNetherrootBase extends BlockFence
{
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");

    protected static final AxisAlignedBB[] BOUNDING_BOXES_DOWN = new AxisAlignedBB[] {new AxisAlignedBB(0.315D, 0.0D, 0.315D, 0.685D, 0.75D, 0.685D), new AxisAlignedBB(0.315D, 0.0D, 0.315D, 0.685D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.315D, 0.685D, 0.75D, 0.685D), new AxisAlignedBB(0.0D, 0.0D, 0.315D, 0.685D, 0.75D, 1.0D), new AxisAlignedBB(0.315D, 0.0D, 0.0D, 0.685D, 0.75D, 0.685D), new AxisAlignedBB(0.315D, 0.0D, 0.0D, 0.685D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.685D, 0.75D, 0.685D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.685D, 0.75D, 1.0D), new AxisAlignedBB(0.315D, 0.0D, 0.315D, 1.0D, 0.75D, 0.685D), new AxisAlignedBB(0.315D, 0.0D, 0.315D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.315D, 1.0D, 0.75D, 0.685D), new AxisAlignedBB(0.0D, 0.0D, 0.315D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.315D, 0.0D, 0.0D, 1.0D, 0.75D, 0.685D), new AxisAlignedBB(0.315D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 0.685D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D)};
    protected static final AxisAlignedBB[] BOUNDING_BOXES_UP = new AxisAlignedBB[] {new AxisAlignedBB(0.315D, 0.45D, 0.315D, 0.685D, 1.0D, 0.685D), new AxisAlignedBB(0.315D, 0.45D, 0.315D, 0.685D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.45D, 0.315D, 0.685D, 1.0D, 0.685D), new AxisAlignedBB(0.0D, 0.45D, 0.315D, 0.685D, 1.0D, 1.0D), new AxisAlignedBB(0.315D, 0.45D, 0.0D, 0.685D, 1.0D, 0.685D), new AxisAlignedBB(0.315D, 0.45D, 0.0D, 0.685D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.45D, 0.0D, 0.685D, 1.0D, 0.685D), new AxisAlignedBB(0.0D, 0.45D, 0.0D, 0.685D, 1.0D, 1.0D), new AxisAlignedBB(0.315D, 0.45D, 0.315D, 1.0D, 1.0D, 0.685D), new AxisAlignedBB(0.315D, 0.45D, 0.315D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.45D, 0.315D, 1.0D, 1.0D, 0.685D), new AxisAlignedBB(0.0D, 0.45D, 0.315D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.315D, 0.45D, 0.0D, 1.0D, 1.0D, 0.685D), new AxisAlignedBB(0.315D, 0.45D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.45D, 0.0D, 1.0D, 1.0D, 0.685D), new AxisAlignedBB(0.0D, 0.45D, 0.0D, 1.0D, 1.0D, 1.0D)};
    protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {new AxisAlignedBB(0.315D, 0.45D, 0.315D, 0.685D, 0.75D, 0.685D), new AxisAlignedBB(0.315D, 0.45D, 0.315D, 0.685D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.45D, 0.315D, 0.685D, 0.75D, 0.685D), new AxisAlignedBB(0.0D, 0.45D, 0.315D, 0.685D, 0.75D, 1.0D), new AxisAlignedBB(0.315D, 0.45D, 0.0D, 0.685D, 0.75D, 0.685D), new AxisAlignedBB(0.315D, 0.45D, 0.0D, 0.685D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.45D, 0.0D, 0.685D, 0.75D, 0.685D), new AxisAlignedBB(0.0D, 0.45D, 0.0D, 0.685D, 0.75D, 1.0D), new AxisAlignedBB(0.315D, 0.45D, 0.315D, 1.0D, 0.75D, 0.685D), new AxisAlignedBB(0.315D, 0.45D, 0.315D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.45D, 0.315D, 1.0D, 0.75D, 0.685D), new AxisAlignedBB(0.0D, 0.45D, 0.315D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.315D, 0.45D, 0.0D, 1.0D, 0.75D, 0.685D), new AxisAlignedBB(0.315D, 0.45D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.45D, 0.0D, 1.0D, 0.75D, 0.685D), new AxisAlignedBB(0.0D, 0.45D, 0.0D, 1.0D, 0.75D, 1.0D)};
    protected static final AxisAlignedBB[] BOUNDING_BOXES_COMBINED = new AxisAlignedBB[] {new AxisAlignedBB(0.315D, 0.0D, 0.315D, 0.685D, 1.0D, 0.685D), new AxisAlignedBB(0.315D, 0.0D, 0.315D, 0.685D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.315D, 0.685D, 1.0D, 0.685D), new AxisAlignedBB(0.0D, 0.0D, 0.315D, 0.685D, 1.0D, 1.0D), new AxisAlignedBB(0.315D, 0.0D, 0.0D, 0.685D, 1.0D, 0.685D), new AxisAlignedBB(0.315D, 0.0D, 0.0D, 0.685D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.685D, 1.0D, 0.685D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.685D, 1.0D, 1.0D), new AxisAlignedBB(0.315D, 0.0D, 0.315D, 1.0D, 1.0D, 0.685D), new AxisAlignedBB(0.315D, 0.0D, 0.315D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.315D, 1.0D, 1.0D, 0.685D), new AxisAlignedBB(0.0D, 0.0D, 0.315D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.315D, 0.0D, 0.0D, 1.0D, 1.0D, 0.685D), new AxisAlignedBB(0.315D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.685D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    protected final String BASE_NAME;

    public BlockNetherrootBase(String name)
    {
        super(Material.WOOD, Material.WOOD.getMaterialMapColor());
        setRegistryName(name);
        setUnlocalizedName(name);
        setDefaultState(blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(UP, Boolean.valueOf(false)).withProperty(DOWN, Boolean.valueOf(false)));
        setCreativeTab(null);
        setTickRandomly(true);
        setHardness(Constants.BLOCK_NETHERROOT_HARDNESS);
        setResistance(Constants.BLOCK_NETHERROOT_RESISTANCE);
        setSoundType(SoundType.WOOD);

        BASE_NAME = name;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(world, pos, state, rand);
        
        checkAndDropBlock(world, pos, state);
    }
    
    protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
    {
        if (!canBlockStay(world, pos, state))
        {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }
    
    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        super.neighborChanged(state, world, pos, block, fromPos);
        checkAndDropBlock(world, pos, state);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = getActualState(state, source, pos);

        if (((Boolean) state.getValue(UP)).booleanValue() && ((Boolean) state.getValue(DOWN)).booleanValue())
        {
            return BOUNDING_BOXES_COMBINED[getBoundingBoxIdx(state)];
        }
        else if (((Boolean) state.getValue(UP)).booleanValue())
        {
            return BOUNDING_BOXES_UP[getBoundingBoxIdx(state)];
        }
        else if (((Boolean) state.getValue(DOWN)).booleanValue())
        {
            return BOUNDING_BOXES_DOWN[getBoundingBoxIdx(state)];
        }
        else
        {
            return BOUNDING_BOXES[getBoundingBoxIdx(state)];
        }
    }

    private static int getBoundingBoxIdx(IBlockState state)
    {
        int i = 0;

        if (((Boolean) state.getValue(NORTH)).booleanValue())
        {
            i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
        }

        if (((Boolean) state.getValue(EAST)).booleanValue())
        {
            i |= 1 << EnumFacing.EAST.getHorizontalIndex();
        }

        if (((Boolean) state.getValue(SOUTH)).booleanValue())
        {
            i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
        }

        if (((Boolean) state.getValue(WEST)).booleanValue())
        {
            i |= 1 << EnumFacing.WEST.getHorizontalIndex();
        }

        return i;
    }

    @Override
    public boolean canConnectTo(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        return block == ModBlocks.BLOCK_NUCLEUS || block == this;
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        Block connector = world.getBlockState(pos.offset(facing)).getBlock();

        if (connector == ModBlocks.BLOCK_NETHERROOT || connector == ModBlocks.BLOCK_NETHERROOT_EXTENSION || connector == ModBlocks.BLOCK_NUCLEUS || connector == ModBlocks.BLOCK_NETHERROOT_END)
        {
            return true;
        }

        return false;
    }

    private boolean canFenceConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        Block block = world.getBlockState(pos.offset(facing)).getBlock();
        return block.canBeConnectedTo(world, pos.offset(facing), facing.getOpposite()) || canConnectTo(world, pos.offset(facing));
    }

    protected boolean canConnectVertical(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        Block block = world.getBlockState(pos.offset(facing)).getBlock();

        return canBeConnectedTo(world, pos, facing) || block.isFullBlock(world.getBlockState(pos.offset(facing)));
    }
    
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean flag)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(worldIn, pos));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH, UP, DOWN});
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH, canFenceConnectTo(worldIn, pos, EnumFacing.NORTH)).withProperty(EAST, canFenceConnectTo(worldIn, pos, EnumFacing.EAST)).withProperty(SOUTH, canFenceConnectTo(worldIn, pos, EnumFacing.SOUTH)).withProperty(WEST, canFenceConnectTo(worldIn, pos, EnumFacing.WEST)).withProperty(UP, canConnectVertical(worldIn, pos, EnumFacing.UP)).withProperty(DOWN, canConnectVertical(worldIn, pos, EnumFacing.DOWN));
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModItems.ITEM_MISC, 1, 3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.ITEM_MISC;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 3;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format(Assets.BLOCK_PREFIX, Assets.ASSET_PREFIX, BASE_NAME);
    }
    
    protected abstract boolean canBlockStay(World world, BlockPos pos, IBlockState state);
}
