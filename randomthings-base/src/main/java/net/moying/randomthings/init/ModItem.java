package net.moying.randomthings.init;

import net.minecraft.world.item.Item;
import net.moying.randomthings.item.charms.ItemCharm;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.moying.randomthings.Constants.MODID;


public class ModItem {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> gluttonyCharm = REGISTRY.register("charm_gluttony", ItemCharm::new);


}
