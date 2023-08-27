package dev.spozap.evfishingcore.models;

import java.util.ArrayList;
import java.util.List;

public class FishingRegion {

    private List<Fish> fishes;
    private String id, displayName;

    public FishingRegion() {
        this.fishes = new ArrayList<>();

        this.fishes.add(new Fish("Caca de pedo culo"));

    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(List<Fish> fishes) {
        this.fishes = fishes;
        this.id = "fishingtest";
        this.displayName = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}