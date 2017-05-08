package soupbubbles.pesquerasnethermod.item;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import soupbubbles.pesquerasnethermod.entity.EntityHellfireBottle;
import soupbubbles.pesquerasnethermod.item.base.ItemBase;
import soupbubbles.pesquerasnethermod.lib.Constants;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.lib.Vanilla;
import soupbubbles.pesquerasnethermod.util.Utils;
import soupbubbles.pesquerasnethermod.util.ModDamageSource;
import soupbubbles.pesquerasnethermod.util.ResourceLocationHelper;

public class ItemHellfireBottle extends ItemBase
{
    public ItemHellfireBottle()
    {
        super(Names.ITEM_BOTTLE_HELLFIRE, Names.ITEM_BOTTLE_SPLASH_HELLFIRE);
        setMaxStackSize(1);
        setHasSubtypes(true);
        setToolTip(true);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving)
    {
        EntityPlayer player = entityLiving instanceof EntityPlayer ? (EntityPlayer) entityLiving : null;

        if (player == null || !player.capabilities.isCreativeMode)
        {
            stack.shrink(1);
        }

        if (!world.isRemote && !player.capabilities.isCreativeMode)
        {
            if (world.getDifficulty() == EnumDifficulty.PEACEFUL)
            {
                Utils.sendMessage(player, Names.CHAT_MESSAGE_BOTTLE_HELLFIRE);
            }
            else
            {
                player.attackEntityFrom(ModDamageSource.HELLFIRE_POTION, Constants.DAMAGE_AMOUNT_HELLFIRE_BOTTLE);
            }
        }

        if (player != null)
        {
            player.addStat(StatList.getObjectUseStats(this));
        }

        if (player == null || !player.capabilities.isCreativeMode)
        {
            if (stack.isEmpty())
            {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (player != null)
            {
                player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn)
    {
        ItemStack stack = player.getHeldItem(handIn);

        if (stack.getItemDamage() == 0)
        {
            player.setActiveHand(handIn);
            return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
        }
        else
        {
            ItemStack itemstack1 = player.capabilities.isCreativeMode ? stack.copy() : stack.splitStack(1);
            world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!world.isRemote)
            {
                EntityHellfireBottle entityBottle = new EntityHellfireBottle(world, player);
                entityBottle.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, -20.0F, 0.5F, 1.0F);
                world.spawnEntity(entityBottle);
            }

            player.addStat(StatList.getObjectUseStats(this));
            return new ActionResult(EnumActionResult.SUCCESS, stack);
        }

    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        ItemStack stack = entityItem.getEntityItem();

        if (entityItem.onGround && !entityItem.world.isRemote)
        {
            if (entityItem.getEntityItem().getItemDamage() == 0)
            {
                //EntityHellfire entity = new EntityHellfire(entityItem.world);
                //entity.setPosition(entityItem.posX, entityItem.posY, entityItem.posZ);
                //entityItem.world.spawnEntity(entity);
            }
            else
            {
                setFire(entityItem.world, entityItem.posX, entityItem.posY, entityItem.posZ);
            }

            entityItem.world.playSound((EntityPlayer) null, entityItem.posX, entityItem.posY, entityItem.posZ, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            entityItem.setDead();
            return true;
        }

        return false;
    }

    public void setFire(World world, double posX, double posY, double posZ)
    {
        for (int x = -1 + (int) posX; x < posX + 1; x++)
        {
            for (int y = -1 + (int) posY; y < posY + 1; y++)
            {
                for (int z = -1 + (int) posZ; z < posZ + 1; z++)
                {
                    BlockPos blockpos = new BlockPos(x, y, z);

                    if (world.rand.nextInt(10) < 5)
                    {
                        if (world.getGameRules().getBoolean(Vanilla.GAMERULE_DO_FIRE_TICK) && world.getBlockState(blockpos).getMaterial() == Material.AIR && Blocks.FIRE.canPlaceBlockAt(world, blockpos))
                        {
                            world.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                        }
                    }
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
}
