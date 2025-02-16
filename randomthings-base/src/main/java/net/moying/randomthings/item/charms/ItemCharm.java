package net.moying.randomthings.item.charms;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ItemCharm extends Item {

    public ItemCharm() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
    }

}
