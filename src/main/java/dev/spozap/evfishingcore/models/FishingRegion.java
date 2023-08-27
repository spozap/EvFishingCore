package dev.spozap.evfishingcore.models;

import java.util.ArrayList;
import java.util.List;

public class FishingRegion {

    private List<Fish> fishes;

    public FishingRegion() {
        this.fishes = new ArrayList<>();

        this.fishes.add(new Fish("Caca de pedo culo"));

    }

    public List<Fish> getFishes() {
        return fishes;
    }
}