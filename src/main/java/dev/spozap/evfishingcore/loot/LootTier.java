package dev.spozap.evfishingcore.loot;

public enum LootTier {

    COMMON("&7&lCOMUN"),
    EPIC("&4&lEPICO"),
    LEGENDARY("&e&lLEGENDARY");

    private final String label;

    LootTier(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
