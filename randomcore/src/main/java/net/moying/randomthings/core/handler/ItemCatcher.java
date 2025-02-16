package net.moying.randomthings.core.handler;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.ArrayList;
import java.util.List;

public class ItemCatcher {

    static boolean catchingDrop = false;
    static List<ItemStack> catchedDrops = new ArrayList<>();

    public static void entityJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide() && event.getEntity() instanceof ItemEntity) {
            if (catchingDrop && !event.isCanceled()) {
                ItemEntity itemEntity = (ItemEntity) event.getEntity();
                itemEntity.setPickUpDelay(50000);
                catchedDrops.add(itemEntity.getItem());
                event.setCanceled(true);
            }
        }
    }

    public static void startCatching() {
        catchingDrop = true;
    }

    public static boolean isCatching() {
        return catchingDrop;
    }

    public static List<ItemStack> stopCatching() {
        ArrayList<ItemStack> ret = new ArrayList<>(catchedDrops);
        catchedDrops.clear();
        catchingDrop = false;
        return ret;
    }

}
