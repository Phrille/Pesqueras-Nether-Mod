package soupbubbles.pesquerasnethermod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import soupbubbles.pesquerasnethermod.block.base.BlockBase;
import soupbubbles.pesquerasnethermod.lib.Constants;
import soupbubbles.pesquerasnethermod.lib.Names;

public class BlockHeart extends BlockBase
{
    public BlockHeart()
    {
        super(Names.BLOCK_HEART);
        setHardness(Constants.BLOCK_HEART_HARDNESS);
        setResistance(Constants.BLOCK_HEART_RESISTANCE);
    }
}
