package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityKhandSoldier extends LOTREntityKhand {
    public LOTREntityKhandSoldier(World world) {
        super(world);
        this.addTargetTasks(true);
        this.spawnRidingHorse = this.rand.nextInt(6) == 0;
        this.npcShield = LOTRShields.ALIGNMENT_KHAND;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.75);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(10);
        if(i == 0 || i == 1 || i == 2) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordKhand));
        }
        else if(i == 3 || i == 4 || i == 5) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeKhand));
        }
        else if(i == 6) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerKhand));
        }
        else if(i == 7) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerKhandPoisoned));
        }
        else if(i == 8) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pikeKhand));
        }
        if(this.rand.nextInt(5) == 0) {
            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearKhand));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootskhand));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legskhand));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodykhand));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetkhand));
        return data;
    }
}