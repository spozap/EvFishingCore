package dev.spozap.evfishingcore.models;

public enum FishTiers {

    COMMON("&7&lCOMUN"),
    EPIC("&4&lEPICO"),
    LEGENDARY("&e&lLEGENDARY");

    private final String label;

    FishTiers(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
