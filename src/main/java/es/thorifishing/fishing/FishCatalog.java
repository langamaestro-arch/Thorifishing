package es.thorifishing.fishing;

import java.util.List;
import static es.thorifishing.fishing.FishSpecies.Rarity.*;

/** Initial design catalog. Catch logic and item registrations are not yet connected. */
public final class FishCatalog {
    private FishCatalog() {}
    public static final List<FishSpecies> SPECIES = List.of(
        new FishSpecies("river_trout", "Trucha de río", COMMON, 0.3, 4.0, "river"),
        new FishSpecies("lake_carp", "Carpa de lago", COMMON, 0.5, 15.0, "lake"),
        new FishSpecies("silver_perch", "Perca plateada", COMMON, 0.2, 3.5, "river"),
        new FishSpecies("blue_tench", "Tenca azul", UNCOMMON, 0.4, 6.0, "swamp"),
        new FishSpecies("deep_catfish", "Siluro profundo", UNCOMMON, 2.0, 35.0, "river"),
        new FishSpecies("ice_char", "Salvelino helado", UNCOMMON, 0.5, 8.0, "frozen_river"),
        new FishSpecies("coral_snapper", "Pargo coralino", COMMON, 0.4, 7.0, "warm_ocean"),
        new FishSpecies("tropical_grouper", "Mero tropical", UNCOMMON, 1.0, 25.0, "warm_ocean"),
        new FishSpecies("moon_eel", "Anguila lunar", RARE, 0.6, 12.0, "ocean_night"),
        new FishSpecies("storm_tuna", "Atún de tormenta", RARE, 4.0, 80.0, "ocean_storm"),
        new FishSpecies("golden_carp", "Carpa dorada", EPIC, 0.7, 12.0, "lake"),
        new FishSpecies("crystal_fish", "Pez cristal", RARE, 0.1, 2.0, "underground_water"),
        new FishSpecies("moss_fish", "Pez musgo", UNCOMMON, 0.1, 4.0, "lush_caves"),
        new FishSpecies("blind_cavefish", "Pez cavernícola", COMMON, 0.1, 1.0, "underground_water"),
        new FishSpecies("glowfin", "Aleta luminosa", RARE, 0.2, 3.0, "deep_ocean"),
        new FishSpecies("abyssal_angler", "Rape abisal", EPIC, 2.0, 25.0, "deep_ocean"),
        new FishSpecies("frost_koi", "Koi escarchado", RARE, 0.5, 8.0, "frozen_river"),
        new FishSpecies("sunscale", "Escama solar", RARE, 0.4, 5.0, "warm_ocean"),
        new FishSpecies("mudskipper", "Saltador del fango", COMMON, 0.1, 1.5, "mangrove_swamp"),
        new FishSpecies("swamp_pike", "Lucio pantanoso", UNCOMMON, 0.7, 13.0, "swamp"),
        new FishSpecies("jungle_arowana", "Arowana selvática", RARE, 1.0, 12.0, "jungle"),
        new FishSpecies("desert_lungfish", "Pez pulmonado", RARE, 0.4, 6.0, "desert_oasis"),
        new FishSpecies("copper_bream", "Brema cobriza", COMMON, 0.2, 5.0, "river"),
        new FishSpecies("shadow_salmon", "Salmón sombrío", RARE, 0.5, 9.0, "dark_forest"),
        new FishSpecies("phantom_ray", "Raya fantasma", EPIC, 3.0, 40.0, "deep_ocean"),
        new FishSpecies("pearl_sturgeon", "Esturión perlado", EPIC, 5.0, 100.0, "river"),
        new FishSpecies("magma_fish", "Pez magma", EPIC, 0.5, 15.0, "nether_lava"),
        new FishSpecies("ember_eel", "Anguila de brasas", RARE, 0.4, 10.0, "nether_lava"),
        new FishSpecies("ender_fish", "Pez del End", LEGENDARY, 1.0, 25.0, "end"),
        new FishSpecies("starwhale", "Ballena estelar", LEGENDARY, 100.0, 500.0, "ocean_event")
    );
}
