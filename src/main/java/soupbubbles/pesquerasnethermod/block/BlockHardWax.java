package soupbubbles.pesquerasnethermod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import soupbubbles.pesquerasnethermod.block.base.BlockBase;
import soupbubbles.pesquerasnethermod.lib.Constants;
import soupbubbles.pesquerasnethermod.lib.Names;

public class BlockHardWax extends BlockBase
{
    public BlockHardWax()
    {
        super(Names.BLOCK_HARD_WAX);
        setHardness(Constants.BLOCK_WAX_HARD_HARDNESS);
        setResistance(Constants.BLOCK_WAX_HARD_RESISTANCE);
    }
}
