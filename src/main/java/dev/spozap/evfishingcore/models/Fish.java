package dev.spozap.evfishingcore.models;

public class Fish {

    private String name;
    private int maxHeight, maxLength;

    public Fish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}