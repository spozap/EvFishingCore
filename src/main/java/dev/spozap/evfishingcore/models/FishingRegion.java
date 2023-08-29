package dev.spozap.evfishingcore.models;

import java.util.ArrayList;
import java.util.List;

public class FishingRegion {
    private FishingLootTable lootTable;
    private String id, displayName;

    public FishingRegion() {

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

    public FishingLootTable getLootTable() {
        return lootTable;
    }
    public void setLootTable(FishingLootTable lootTable) {
        this.lootTable = lootTable;
    }
}