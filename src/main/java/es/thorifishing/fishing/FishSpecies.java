package es.thorifishing.fishing;

/** Immutable definition for a fish species; no client-only dependencies. */
public record FishSpecies(String id, String name, Rarity rarity, double minKg, double maxKg, String habitat) {
    public enum Rarity { COMMON, UNCOMMON, RARE, EPIC, LEGENDARY }
    public FishSpecies {
        if (id == null || id.isBlank() || name == null || name.isBlank()) throw new IllegalArgumentException("Fish id and name required");
        if (rarity == null || habitat == null || habitat.isBlank()) throw new IllegalArgumentException("Rarity and habitat required");
        if (minKg <= 0 || maxKg < minKg) throw new IllegalArgumentException("Invalid fish weight range");
    }
}
