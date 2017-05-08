package soupbubbles.pesquerasnethermod.util;

import net.minecraft.util.DamageSource;
import soupbubbles.pesquerasnethermod.lib.Names;
import soupbubbles.pesquerasnethermod.lib.Reference;

public class ModDamageSource extends DamageSource
{
    public static final DamageSource HELLFIRE_POTION = new ModDamageSource(Names.DAMAGE_SOURCE_BOTTLE_HELLFIRE).setDamageBypassesArmor();
    public static final DamageSource THORN_BUSH = new ModDamageSource(Names.DAMAGE_SOURCE_THORN_BUSH);

    public ModDamageSource(String name)
    {
        super(Reference.MOD_ID + "." + name);
    }
}
