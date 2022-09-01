package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityRedDwarfWarrior extends LOTREntityRedDwarf{
	 public LOTREntityRedDwarfWarrior(World world) {
	        super(world);
	        this.npcShield = LOTRShields.ALIGNMENT_RED_MOUNTAINS;
	    }

	    @Override
	    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
	        data = super.onSpawnWithEgg(data);
	        int i = this.rand.nextInt(7);
	        if(i == 0) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.sword_red_dwarven));
	        }
	        else if(i == 1 || i == 2) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxe_red_dwarven));
	        }
	        else if(i == 3 || i == 4) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hammer_red_dwarven));
	        }
	        else if(i == 5) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.mattock_red_dwarven));
	        }
	        else if(i == 6) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pike_red_dwarven));
	        }
	        if(this.rand.nextInt(6) == 0) {
	            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spear_red_dwarven));
	        }
	        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
	        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.boots_red_dwarven));
	        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legs_red_dwarven));
	        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.body_red_dwarven));
	        if(this.rand.nextInt(10) != 0) {
	            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmet_red_dwarven));
	        }
	        return data;
	    }

	    @Override
	    public float getAlignmentBonus() {
	        return 2.0f;
	    }
	}

