package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRItemHammerStory extends LOTRItemBattleaxe {
    public LOTRItemHammerStory(LOTRMaterial material) {
        this(material.toToolMaterial());
        this.lotrWeaponDamage += 2.5f;
        this.setCreativeTab(LOTRCreativeTabs.tabStory);
    }

    public LOTRItemHammerStory(Item.ToolMaterial material) {
        super(material);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.none;
    }
}
