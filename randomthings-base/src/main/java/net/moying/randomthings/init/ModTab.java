package net.moying.randomthings.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.moying.randomthings.Constants.MODID;

public class ModTab {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB_RANDOM_THINGS = REGISTRY.register("tab_random_things",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.randomthings.tab_random_things")).icon(() -> new ItemStack(ModItem.gluttonyCharm.get())).displayItems((parameters, tabData) -> {
                tabData.accept(ModItem.gluttonyCharm.get());
            }).build());
}

