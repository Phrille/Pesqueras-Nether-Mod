package soupbubbles.pesquerasnethermod.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import soupbubbles.pesquerasnethermod.creativetab.CreativeTab;
import soupbubbles.pesquerasnethermod.lib.Assets;

public abstract class BlockBase extends Block
{
    private final String BASE_NAME;

    public BlockBase(String name)
    {
        this(name, Material.ROCK);
    }

    public BlockBase(String name, Material material)
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