package dev.spozap.evfishingcore.regions;

public class FishingRegion {
    private RegionLootTable lootTable;
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

    public RegionLootTable getLootTable() {
        return lootTable;
    }
    public void setLootTable(RegionLootTable lootTable) {
        this.lootTable = lootTable;
    }
}