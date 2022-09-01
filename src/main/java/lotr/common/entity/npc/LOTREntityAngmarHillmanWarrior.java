package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityAngmarHillmanWarrior extends LOTREntityAngmarHillman {
    private static ItemStack[] weapons = new ItemStack[] {new ItemStack(LOTRMod.swordRudaur),
	new ItemStack(LOTRMod.battleaxeRudaur), 
	new ItemStack(LOTRMod.spearRudaur)};
    private static ItemStack[] helmets = new ItemStack[] {new ItemStack(LOTRMod.helmetrudaur)};
    private static ItemStack[] bodies = new ItemStack[] {new ItemStack(LOTRMod.bodyrudaur)};
    private static ItemStack[] legs = new ItemStack[] {new ItemStack(LOTRMod.legsrudaur)};
    private static ItemStack[] boots = new ItemStack[] {new ItemStack(LOTRMod.bootsrudaur)};

    public LOTREntityAngmarHillmanWarrior(World world) {
        super(world);
        this.npcShield = LOTRShields.ALIGNMENT_ANGMAR;
    }

    @Override
    public EntityAIBase getHillmanAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.6, false);
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(weapons.length);
        this.npcItemsInv.setMeleeWeapon(weapons[i].copy());
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        i = this.rand.nextInt(boots.length);
        this.setCurrentItemOrArmor(1, boots[i].copy());
        i = this.rand.nextInt(legs.length);
        this.setCurrentItemOrArmor(2, legs[i].copy());
        i = this.rand.nextInt(bodies.length);
        this.setCurrentItemOrArmor(3, bodies[i].copy());
        if(this.rand.nextInt(5) != 0) {
            i = this.rand.nextInt(helmets.length);
            this.setCurrentItemOrArmor(4, helmets[i].copy());
        }
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public void dropHillmanItems(boolean flag, int i) {
    }
}
