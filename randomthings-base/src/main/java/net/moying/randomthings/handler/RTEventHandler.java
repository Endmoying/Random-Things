package net.moying.randomthings.handler;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.moying.randomthings.core.handler.ItemCatcher;
import net.moying.randomthings.item.charms.CharmEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class RTEventHandler {

    @SubscribeEvent
    public void livingEntityUseItem(LivingEntityUseItemEvent.Tick event) {
        CharmEffects.handleGluttonCharm(event);
    }

    static Player player;

    @SubscribeEvent
    public void entityJoinLevel(EntityJoinLevelEvent event) {
        ItemCatcher.entityJoinWorld(event);
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        ItemStack tool = event.getPlayer().getMainHandItem();
        if (tool != ItemStack.EMPTY && tool.getEnchantmentLevel(event.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("randomthings:magnetic")))) != 0) {
            ItemCatcher.startCatching();
            player = event.getPlayer();
        }
    }

}
