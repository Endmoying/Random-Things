package net.moying.randomthings.core.handler;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class AsmHandler {

    static Player player;

    public static void postHarvest() {
        if (ItemCatcher.isCatching() && player != null) {
            for (ItemStack is : ItemCatcher.stopCatching()) {
                ItemStack stack = is.copy();
                ItemEntity fakeEntity = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), stack);

                ItemEntityPickupEvent event = new ItemEntityPickupEvent.Pre(player, fakeEntity);

                if (!(NeoForge.EVENT_BUS.post(event) instanceof ICancellableEvent iCancellableEvent) || !iCancellableEvent.isCanceled()) {
                    ItemHandlerHelper.giveItemToPlayer(player, stack);
                }
            }
            player = null;
        }
    }

}
