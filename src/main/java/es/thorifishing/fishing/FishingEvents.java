package es.thorifishing.fishing;

import es.thorifishing.Thorifishing;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Server-authoritative fishing encounter.
 * When a vanilla fish bites and the player reels in, loot is held until the
 * player wins a short tension minigame by tapping right click with the rod.
 */
public final class FishingEvents {
    private static final String XP_KEY = "thorifishing_xp";
    private static final String CATCHES_KEY = "thorifishing_catches";
    private final Map<UUID, Encounter> encounters = new HashMap<>();
    private final Random random = new Random();

    private static final class Encounter {
        final ArrayList<ItemStack> loot = new ArrayList<>();
        final int difficulty;
        int tension = 28;
        int stamina;
        int ticks = 0;
        int lastTap = -100;
        Encounter(int difficulty) {
            this.difficulty = difficulty;
            this.stamina = 65 + difficulty * 35;
        }
    }

    @SubscribeEvent
    public void onItemFished(ItemFishedEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (encounters.containsKey(player.getUUID())) return;
        boolean caughtFish = event.getDrops().stream().anyMatch(stack ->
            stack.is(Items.COD) || stack.is(Items.SALMON) ||
            stack.is(Items.TROPICAL_FISH) || stack.is(Items.PUFFERFISH));
        if (!caughtFish) return;

        int roll = random.nextInt(100);
        int difficulty = roll < 8 ? 3 : roll < 30 ? 2 : 1;
        Encounter encounter = new Encounter(difficulty);
        for (ItemStack stack : event.getDrops()) encounter.loot.add(stack.copy());
        event.getDrops().clear();
        encounters.put(player.getUUID(), encounter);
        player.displayClientMessage(Component.literal("§b¡Pez enganchado! §fPulsa clic derecho repetidamente con la caña para recoger sedal"), false);
        showHud(player, encounter);
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        Encounter encounter = encounters.get(player.getUUID());
        if (encounter == null) return;
        if (!event.getItemStack().is(Items.FISHING_ROD)) return;
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
        if (encounter.ticks - encounter.lastTap < 3) return;
        encounter.lastTap = encounter.ticks;
        encounter.stamina -= 9;
        encounter.tension += 9 + encounter.difficulty * 2;
        if (encounter.stamina <= 0) win(player, encounter);
        else if (encounter.tension >= 100) lose(player);
        else showHud(player, encounter);
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        Encounter encounter = encounters.get(player.getUUID());
        if (encounter == null) return;
        encounter.ticks++;
        // Releasing the reel lets the line relax, while the fish tires the player.
        if (encounter.ticks % 4 == 0) encounter.tension = Math.max(0, encounter.tension - 5);
        if (encounter.ticks % 20 == 0) {
            encounter.tension += 5 + encounter.difficulty * 3;
            showHud(player, encounter);
        }
        if (encounter.tension >= 100 || encounter.ticks > 20 * 35 || !player.isAlive()) lose(player);
    }

    private void showHud(ServerPlayer player, Encounter e) {
        String bar = "§a" + "|".repeat(Math.max(0, (100 - e.tension) / 10))
            + "§c" + "|".repeat(Math.min(10, e.tension / 10));
        player.displayClientMessage(Component.literal("§bThorifishing §7| §fTensión [" + bar
            + "§f] §7| §eResistencia: " + Math.max(0, e.stamina)
            + " §7| §f" + (e.difficulty == 3 ? "Legendario" : e.difficulty == 2 ? "Raro" : "Común")), true);
    }

    private void lose(ServerPlayer player) {
        encounters.remove(player.getUUID());
        player.displayClientMessage(Component.literal("§cThorifishing: ¡el pez ha escapado!"), true);
    }

    private void win(ServerPlayer player, Encounter encounter) {
        encounters.remove(player.getUUID());
        for (ItemStack stack : encounter.loot) {
            if (!player.getInventory().add(stack)) player.drop(stack, false);
        }
        int bonusXp = 10 + random.nextInt(16) + (encounter.difficulty - 1) * 10;
        int previousXp = player.getPersistentData().getIntOr(XP_KEY, 0);
        int currentXp = Math.min(Integer.MAX_VALUE - bonusXp, previousXp) + bonusXp;
        player.getPersistentData().putInt(XP_KEY, currentXp);
        int catches = player.getPersistentData().getIntOr(CATCHES_KEY, 0) + 1;
        player.getPersistentData().putInt(CATCHES_KEY, catches);
        if (random.nextInt(100) < 12) {
            ItemStack bonus = new ItemStack(random.nextInt(100) < 20
                ? Thorifishing.GOLDEN_CARP.get() : Thorifishing.CRYSTAL_FISH.get());
            if (!player.getInventory().add(bonus)) player.drop(bonus, false);
        }
        int level = FishingProgress.levelForXp(currentXp);
        player.displayClientMessage(Component.literal("§a¡Capturado! §e+" + bonusXp
            + " XP §7| §fNivel " + level + "/5 §7| §fCapturas " + catches), true);
        if (level > FishingProgress.levelForXp(previousXp)) {
            player.sendSystemMessage(Component.literal("§6Thorifishing: ¡nivel de pesca " + level + "/5!"));
        }
    }
}
