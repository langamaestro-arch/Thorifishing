package es.thorifishing.fishing;

import es.thorifishing.Thorifishing;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;

import java.util.Random;

/**
 * Server-side first playable fishing loop.
 * Vanilla fishing remains intact; custom catches and experience are additive.
 */
public final class FishingEvents {
    private static final String XP_KEY = "thorifishing_xp";
    private static final String CATCHES_KEY = "thorifishing_catches";

    @SubscribeEvent
    public void onItemFished(ItemFishedEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        boolean caughtFish = event.getDrops().stream().anyMatch(stack ->
            stack.is(Items.COD) || stack.is(Items.SALMON) ||
            stack.is(Items.TROPICAL_FISH) || stack.is(Items.PUFFERFISH));
        if (!caughtFish) return;

        Random random = new Random();
        int bonusXp = 10 + random.nextInt(16);
        int previousXp = player.getPersistentData().getInt(XP_KEY);
        int previousLevel = FishingProgress.levelForXp(previousXp);
        int currentXp = Math.min(Integer.MAX_VALUE - bonusXp, previousXp) + bonusXp;
        player.getPersistentData().putInt(XP_KEY, currentXp);
        int catches = player.getPersistentData().getInt(CATCHES_KEY) + 1;
        player.getPersistentData().putInt(CATCHES_KEY, catches);

        // Bonus species are deliberately rare; standard vanilla loot is preserved.
        if (random.nextInt(100) < 12) {
            boolean rare = random.nextInt(100) < 20;
            ItemStack bonus = new ItemStack(rare ? Thorifishing.GOLDEN_CARP.get() : Thorifishing.CRYSTAL_FISH.get());
            event.getDrops().add(bonus);
            player.sendSystemMessage(Component.literal("Thorifishing: ¡captura especial! +" + bonusXp + " XP"));
        } else {
            player.sendSystemMessage(Component.literal("Thorifishing: +" + bonusXp + " XP | Capturas: " + catches));
        }

        int newLevel = FishingProgress.levelForXp(currentXp);
        if (newLevel > previousLevel) {
            player.sendSystemMessage(Component.literal("Thorifishing: ¡nivel de pesca " + newLevel + "/5!"));
        }
    }
}
