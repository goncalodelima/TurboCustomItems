package pt.gongas.customitems.platforms.bukkit.model;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CustomItem {

    private final String identifier;
    private final XMaterial material;
    private final String name;
    private final List<String> lore;
    private final Map<XEnchantment, Integer> enchantments;
    private final Set<XItemFlag> flags;
    private final Set<String> nbt;
    private final boolean stackable;

    public CustomItem(String identifier, XMaterial material, String name, List<String> lore, Map<XEnchantment, Integer> enchantments, Set<XItemFlag> flags, Set<String> nbt, boolean stackable) {
        this.identifier = identifier;
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.enchantments = enchantments;
        this.flags = flags;
        this.nbt = nbt;
        this.stackable = stackable;
    }

    public String getIdentifier() {
        return identifier;
    }

    public XMaterial getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public Map<XEnchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public Set<XItemFlag> getFlags() {
        return flags;
    }

    public Set<String> getNbt() {
        return nbt;
    }

    public boolean isStackable() {
        return stackable;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CustomItem)) return false;
        CustomItem that = (CustomItem) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier);
    }

}
