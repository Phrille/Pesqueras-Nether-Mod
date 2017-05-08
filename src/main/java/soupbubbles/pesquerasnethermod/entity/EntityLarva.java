package soupbubbles.pesquerasnethermod.entity;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import soupbubbles.pesquerasnethermod.lib.Constants;

public class EntityLarva extends EntityMob
{
    public EntityLarva(World world)
    {
        super(world);
        setSize(0.4F, 0.3F);
    }

    @Override
    protected void initEntityAI()
    {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public double getYOffset()
    {
        return 0.1D;
    }

    @Override
    public float getEyeHeight()
    {
        return 0.1F;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Constants.ENTITY_LARVA_MAX_HEALTH);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(Constants.ENTITY_LARVA_MOVEMENT_SPEED);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Constants.ENTITY_LARVA_ATTACK_DAMAGE);
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return null;
    }

    @Override
    public void onUpdate()
    {
        renderYawOffset = rotationYaw;
        super.onUpdate();
    }

    @Override
    public void setRenderYawOffset(float offset)
    {
        rotationYaw = offset;
        super.setRenderYawOffset(offset);
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        if (super.getCanSpawnHere())
        {
            EntityPlayer entityplayer = world.getNearestPlayerNotCreative(this, 5.0D);
            return entityplayer == null;
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
}