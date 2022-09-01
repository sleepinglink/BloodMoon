package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class LOTRItemShovel extends ItemSpade {
    public LOTRItemShovel(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemShovel(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab(LOTRCreativeTabs.tabTools);
    }
}
