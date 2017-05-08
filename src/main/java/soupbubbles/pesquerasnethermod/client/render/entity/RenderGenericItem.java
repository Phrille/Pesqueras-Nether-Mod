package soupbubbles.pesquerasnethermod.client.render.entity;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RenderGenericItem extends RenderSnowball
{
	private int damage;
	
	public RenderGenericItem(RenderManager renderManager, Item item, int itemDamage, RenderItem renderItem)
	{
		super(renderManager, item, renderItem);
		damage = itemDamage;
	}
	
	@Override
    public ItemStack getStackToRender(Entity entity)
    {
        return new ItemStack(item, 1, damage);
    }

}
