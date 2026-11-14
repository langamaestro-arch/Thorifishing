package es.thorifishing;

import com.mojang.logging.LogUtils;
import es.thorifishing.fishing.FishingEvents;
import net.neoforged.neoforge.common.NeoForge;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(Thorifishing.MOD_ID)
public final class Thorifishing {
    public static final String MOD_ID = "thorifishing";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredItem<Item> WORM_BAIT = ITEMS.registerSimpleItem("worm_bait", Item.Properties::new);
    public static final DeferredItem<Item> CRYSTAL_FISH = ITEMS.registerSimpleItem("crystal_fish", Item.Properties::new);
    public static final DeferredItem<Item> GOLDEN_CARP = ITEMS.registerSimpleItem("golden_carp", Item.Properties::new);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FISHING_TAB = TABS.register("fishing", () ->
        CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.thorifishing"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> CRYSTAL_FISH.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(WORM_BAIT.get());
                output.accept(CRYSTAL_FISH.get());
                output.accept(GOLDEN_CARP.get());
            }).build());

    public Thorifishing(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        TABS.register(modEventBus);
        NeoForge.EVENT_BUS.register(new FishingEvents());
        LOGGER.info("Thorifishing 0.1.0: registered starter fishing items");
    }
}
