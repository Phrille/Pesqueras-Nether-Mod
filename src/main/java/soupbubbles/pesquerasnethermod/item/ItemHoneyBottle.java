package soupbubbles.pesquerasnethermod.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import soupbubbles.pesquerasnethermod.item.base.ItemBase;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.util.ResourceLocationHelper;

public class ItemHoneyBottle extends ItemBase
{
    public ItemHoneyBottle()
    {
        super(Names.ITEM_BOTTLE_HONEY);
        setMaxStackSize(1);
        setToolTip(true);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving)
    {
        EntityPlayer entityPlayer = entityLiving instanceof EntityPlayer ? (EntityPlayer) entityLiving : null;

        if (entityPlayer == null || !entityPlayer.capabilities.isCreativeMode)
        {
            stack.shrink(1);
        }

        if (!world.isRemote)
        {
            // drink honey
        }

        if (entityPlayer != null)
        {
            entityPlayer.addStat(StatList.getObjectUseStats(this));
        }

        if (entityPlayer == null || !entityPlayer.capabilities.isCreativeMode)
        {
            if (stack.isEmpty())
            {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (entityPlayer != null)
            {
                entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
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
        player.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
    }
}
