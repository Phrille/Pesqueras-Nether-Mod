package soupbubbles.pesquerasnethermod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import soupbubbles.pesquerasnethermod.PesquerasNetherMod;
import soupbubbles.pesquerasnethermod.block.base.BlockBase;
import soupbubbles.pesquerasnethermod.lib.Constants;
import soupbubbles.pesquerasnethermod.lib.Names;

public class BlockWax extends BlockBase
{
    public BlockWax()
    {
        super(Names.BLOCK_WAX, Material.GROUND);
        setHardness(Constants.BLOCK_WAX_HARDNESS);
        setResistance(Constants.BLOCK_WAX_RESISTANCE);
        setSoundType(SoundType.SLIME);
    }
    
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        entityIn.motionX *= 0.4D;
        entityIn.motionZ *= 0.4D;
    }
}
