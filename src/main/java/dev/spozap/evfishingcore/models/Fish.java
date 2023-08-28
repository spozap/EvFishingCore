package dev.spozap.evfishingcore.models;

public class Fish {

    private String name;
    private int maxHeight, maxLength;
    private FishTiers tier;

    public Fish() {

    }

    public Fish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTier(FishTiers tier) {
        this.tier = tier;
    }

    public FishTiers getTier() {
        return tier;
    }
}