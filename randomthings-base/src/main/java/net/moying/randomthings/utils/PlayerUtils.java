package net.moying.randomthings.utils;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtils {

    public static List<ItemStack> getStacksFromPlayer (Player player, Item item) {

        final List<ItemStack> items = new ArrayList<>();

        for (final ItemStack stack : player.getInventory().items) {
            if (stack != null && stack.getItem() == item) {
                items.add(stack);
            }
        }

        for (final EquipmentSlot slotType : EquipmentSlot.values()) {

            final ItemStack stack = player.getItemBySlot(slotType);

            if (stack.getItem() == item) {

                items.add(stack);
            }
        }

        return items;
    }

}
