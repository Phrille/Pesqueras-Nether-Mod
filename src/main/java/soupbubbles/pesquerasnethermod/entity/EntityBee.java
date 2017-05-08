package soupbubbles.pesquerasnethermod.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import soupbubbles.pesquerasnethermod.lib.Constants;

public class EntityBee extends EntityZombie
{
    public EntityBee(World world)
    {
        super(world);
        isImmuneToFire = true;
    }

    @Override
    protected void initEntityAI()
    {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, false));
        tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D));
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(4, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Constants.ENTITY_BEE_MAX_HEALTH);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(Constants.ENTITY_BEE_MOVEMENT_SPEED);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Constants.ENTITY_BEE_ATTACK_DAMAGE);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    @Override
    public boolean isNotColliding()
    {
        return world.checkNoEntityCollision(getEntityBoundingBox(), this) && world.getCollisionBoxes(this, getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(getEntityBoundingBox());
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound()
    {
        return null;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound()
    {
        return null;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        return false;
    }

    protected ItemStack getSkullDrop()
    {
        return ItemStack.EMPTY;
    }
}