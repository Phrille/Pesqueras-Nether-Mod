package soupbubbles.pesquerasnethermod.block.base;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import soupbubbles.pesquerasnethermod.creativetab.CreativeTab;
import soupbubbles.pesquerasnethermod.lib.Assets;

public abstract class BlockTileEntityBase extends BlockContainer
{
    private final String BASE_NAME;

	public BlockTileEntityBase (String name, Material material)
	{
		super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTab.PESQUERASNETHERMOD_TAB);
        setSoundType(SoundType.STONE);

        BASE_NAME = name;
	}
	
    @Override
    public String getUnlocalizedName()
    {
        return String.format(Assets.BLOCK_PREFIX, Assets.ASSET_PREFIX, BASE_NAME);
    }
}
