package pt.gongas.customitems.platforms.bukkit.model;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;
import pt.gongas.customitems.shared.customitem.CustomItem;

import java.util.*;

public final class BukkitCustomItem implements CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack> {

    private final String identifier;
    private final XMaterial material;
    private final String name;
    private final List<String> lore;
    private final Map<XEnchantment, Integer> enchantments;
    private final Set<XItemFlag> flags;
    private final Set<String> nbt;
    private final ItemStack item;
    private final boolean stackable;

    public BukkitCustomItem(String identifier, XMaterial material, String name, List<String> lore, Map<XEnchantment, Integer> enchantments, Set<XItemFlag> flags, Set<String> nbt, ItemStack item, boolean stackable) {
        this.identifier = Objects.requireNonNull(identifier, "identifier");
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.enchantments = enchantments;
        this.flags = flags;
        this.nbt = nbt;
        this.item = item;
        this.stackable = stackable;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public XMaterial getMaterial() {
        return material;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public Map<XEnchantment, Integer> getEnchantments() {
        return enchantments;
    }

    @Override
    public Set<XItemFlag> getFlags() {
        return flags;
    }

    @Override
    public Set<String> getNbt() {
        return nbt;
    }

    @Override
    public ItemStack getItem() {
        return item.clone();
    }

    @Override
    public boolean isStackable() {
        return stackable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BukkitCustomItem)) return false;
        BukkitCustomItem that = (BukkitCustomItem) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier);
    }

}