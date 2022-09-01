package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.item.Item;

public class LOTRItemPikeStory extends LOTRItemPike {
    public LOTRItemPikeStory(LOTRMaterial material) {
        this(material.toToolMaterial());
        this.setCreativeTab(LOTRCreativeTabs.tabStory);
    }

    public LOTRItemPikeStory(Item.ToolMaterial material) {
        super(material);
    }
}
