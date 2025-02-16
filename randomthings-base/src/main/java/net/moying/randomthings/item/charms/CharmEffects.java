package net.moying.randomthings.item.charms;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.moying.randomthings.init.ModItem;
import net.moying.randomthings.utils.PlayerUtils;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.ArrayList;
import java.util.List;

public class CharmEffects {

    public static void handleGluttonCharm(LivingEntityUseItemEvent.Tick event) {
        if (event.getEntity() instanceof Player) {
            final Item charm = ModItem.gluttonyCharm.get();
            final Player player = (Player) event.getEntity();
            final ItemStack using = event.getItem();
            if ((using.has(DataComponents.FOOD) || using.getUseAnimation() == UseAnim.DRINK || using.getUseAnimation() == UseAnim.EAT) && !getStacksFromPlayer(player, charm).isEmpty()) {
                event.setDuration(1);
            }
        }

    }

    public static List<ItemStack> getStacksFromPlayer(Player player, Item charm) {
        final List<ItemStack> stacks = new ArrayList<>();

//        if (ModList.get().isLoaded("curios")) {
//            stacks.addAll();
//        }
        stacks.addAll(PlayerUtils.getStacksFromPlayer(player, charm));
        return stacks;
    }

}
