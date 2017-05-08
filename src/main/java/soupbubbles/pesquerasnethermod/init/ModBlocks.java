package soupbubbles.pesquerasnethermod.init;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import soupbubbles.pesquerasnethermod.block.BlockHardWax;
import soupbubbles.pesquerasnethermod.block.BlockHeart;
import soupbubbles.pesquerasnethermod.block.BlockNetherroot;
import soupbubbles.pesquerasnethermod.block.BlockNucleus;
import soupbubbles.pesquerasnethermod.block.BlockThornBush;
import soupbubbles.pesquerasnethermod.block.BlockWax;
import soupbubbles.pesquerasnethermod.lib.Names;

public class ModBlocks
{
    public static final Set<Block> BLOCKS = new HashSet<>();

    public static final BlockWax BLOCK_WAX;
    public static final BlockHardWax BLOCK_HARD_WAX;
    public static final BlockThornBush BLOCK_THORN_BUSH;
    public static final BlockNucleus BLOCK_NUCLEUS;
    public static final BlockNetherroot BLOCK_NETHERROOT;
    public static final BlockNetherroot BLOCK_NETHERROOT_EXTENSION;
    public static final BlockNetherroot BLOCK_NETHERROOT_END;
    public static final BlockHeart BLOCK_HEART;

    static
    {
        BLOCK_WAX = registerBlock(new BlockWax());
        BLOCK_HARD_WAX = registerBlock(new BlockHardWax());
        BLOCK_THORN_BUSH = registerBlock(new BlockThornBush());
        BLOCK_NUCLEUS = registerBlock(new BlockNucleus());
        BLOCK_NETHERROOT = registerBlock(new BlockNetherroot(Names.BLOCK_NETHERROOT));
        BLOCK_NETHERROOT_EXTENSION = registerBlock(new BlockNetherroot(Names.BLOCK_NETHERROOT_EXTENSION));
        BLOCK_NETHERROOT_END = registerBlock(new BlockNetherroot(Names.BLOCK_NETHERROOT_END));
        BLOCK_HEART = registerBlock(new BlockHeart());
    }

    public static void registerBlocks()
    {
    }
    
    public static void registerTileEntities()
    {
    }

    protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block)
    {
        return registerBlock(block, ItemBlock::new);
    }

    protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block, @Nullable Function<BLOCK, ItemBlock> itemFactory)
    {
        GameRegistry.register(block);

        if (itemFactory != null)
        {
            final ItemBlock itemBlock = itemFactory.apply(block);

            GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
        }

        BLOCKS.add(block);
        return block;
    }
}
