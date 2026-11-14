package es.thorifishing.fishing;

/** Pure progression rules. Persistence and server hooks will be added separately. */
public final class FishingProgress {
    private static final int[] LEVEL_XP = {0, 100, 350, 900, 2000};
    private FishingProgress() {}
    public static int levelForXp(int xp) {
        if (xp < 0) throw new IllegalArgumentException("XP cannot be negative");
        int level = 1;
        for (int i = 1; i < LEVEL_XP.length; i++) if (xp >= LEVEL_XP[i]) level = i + 1;
        return level;
    }
    public static int xpForLevel(int level) {
        if (level < 1 || level > 5) throw new IllegalArgumentException("Level must be 1-5");
        return LEVEL_XP[level - 1];
    }
}
