package dev.spozap.evfishingcore.loot;

import dev.spozap.evfishingcore.utils.NumberUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fish extends LootItem {

    private double maxWeight, maxLength, weight, length;
    private Random random;

    public Fish() {
        super();
        this.random = new Random();
    }

    public double getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }
    public double getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    public void generateStats() {
        this.weight = NumberUtils.roundDouble(random.nextDouble(maxWeight));
        this.length = NumberUtils.roundDouble(random.nextDouble(maxLength));
    }

    @Override
    public ItemStack toItemStack() {
        generateStats();

        ItemStack fishItem = new ItemStack(this.getMaterial());
        ItemMeta fishMeta = fishItem.getItemMeta();

        LocalDateTime currentDateTime = LocalDateTime.now();

        // Formatear la fecha y hora en una cadena de texto
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        List<Component> lore = new ArrayList<>();

        lore.add(Component.text("Estadisticas").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        lore.add(Component.text(""));
        lore.add(Component.text(
                new StringBuilder("■ Grado: ").append(ChatColor.translateAlternateColorCodes('&', getTier().getLabel())).toString())
                .color(NamedTextColor.GRAY).color(NamedTextColor.GRAY));
        lore.add(Component.text(
           new StringBuilder("■ Peso: ").append(    weight).toString()
        ).color(NamedTextColor.GRAY));
        lore.add(Component.text(
           new StringBuilder("■ Longitud: ").append(length).toString()
        ).color(NamedTextColor.GRAY));
        lore.add(Component.text(""));
        lore.add(Component.text(
                new StringBuilder("Capturado el: ").append(formattedDateTime).toString()
        ).color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD, TextDecoration.ITALIC));

        fishMeta.displayName(Component.text(ChatColor.translateAlternateColorCodes('&', getName())));
        fishMeta.lore(lore);

        fishItem.setItemMeta(fishMeta);

        return fishItem;
    }
}