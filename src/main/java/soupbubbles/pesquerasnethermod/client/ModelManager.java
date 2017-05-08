package soupbubbles.pesquerasnethermod.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import soupbubbles.pesquerasnethermod.init.ModBlocks;
import soupbubbles.pesquerasnethermod.init.ModFluids;
import soupbubbles.pesquerasnethermod.init.ModItems;
import soupbubbles.pesquerasnethermod.item.base.ItemBase;
import soupbubbles.pesquerasnethermod.lib.Assets;

@SideOnly(Side.CLIENT)
public class ModelManager
{
    public static final ModelManager INSTANCE = new ModelManager();
    private Set<Item> itemsRegistered = new HashSet<>();

    private static final String FLUID_MODEL_PATH = Assets.TEXTURE_PREFIX + "fluid";
    
    private ModelManager()
    {
    }

    public void registerAllModels()
    {
        registerFluidModels();
        registerBlockModels();
        registerItemModels();
    }

    private void registerFluidModels()
    {
        ModFluids.MOD_FLUID_BLOCKS.forEach(this::registerFluidModel);
    }

    private void registerBlockModels()
    {
        ModBlocks.BLOCKS.stream().filter(block -> !itemsRegistered.contains(Item.getItemFromBlock(block))).forEach(this::initBlockModels);
    }

    private void registerItemModels()
    {
        ModItems.ITEMS.stream().filter(item -> !itemsRegistered.contains(item)).forEach(this::initItemModelsAndVariants);
    }

    private void registerFluidModel(IFluidBlock fluidBlock)
    {
        final Item item = Item.getItemFromBlock((Block) fluidBlock);
        assert item != null;

        ModelBakery.registerItemVariants(item);

        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FLUID_MODEL_PATH, fluidBlock.getFluid().getName());

        ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelResourceLocation));

        ModelLoader.setCustomStateMapper((Block) fluidBlock, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return modelResourceLocation;
            }
        });

        itemsRegistered.add(item);
    }

    private void initItemModelsAndVariants(Item item)
    {
        if (item instanceof ItemBase)
        {
            ItemBase itemBase = (ItemBase) item;

            if (item.getHasSubtypes() && itemBase.getVariants().length > 0)
            {
                List<ModelResourceLocation> modelResources = new ArrayList<>();

                for (int i = 0; i < itemBase.getVariants().length; i++)
                {
                    modelResources.add(new ModelResourceLocation(Assets.TEXTURE_PREFIX + itemBase.getVariants()[i]));
                }

                ModelBakery.registerItemVariants(itemBase, modelResources.toArray(new ModelResourceLocation[0]));
                ModelLoader.setCustomMeshDefinition(itemBase, itemStack -> modelResources.get(itemStack.getMetadata()));
            }
            else
            {
                ModelLoader.setCustomModelResourceLocation(itemBase, 0, new ModelResourceLocation(itemBase.getRegistryName().toString()));
            }
        }
        else
        {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
        }
        
        itemsRegistered.add(item);
    }

    private void initBlockModels(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName().toString()));
        itemsRegistered.add(Item.getItemFromBlock(block));
    }
}