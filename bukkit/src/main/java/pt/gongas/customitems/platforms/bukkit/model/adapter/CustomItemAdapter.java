package pt.gongas.customitems.platforms.bukkit.model.adapter;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.configuration.ConfigurationSection;
import pt.gongas.customitems.platforms.bukkit.model.CustomItem;
import pt.gongas.customitems.platforms.bukkit.util.config.ConfigurationAdapter;
import pt.gongas.customitems.shared.util.exception.PluginStartupException;

import java.util.*;

public class CustomItemAdapter implements ConfigurationAdapter<CustomItem> {

    private String identifier;

    public CustomItem adapt(String identifier, ConfigurationSection section) {
        this.identifier = identifier;
        return adapt(section);
    }

    @Override
    public CustomItem adapt(ConfigurationSection section) {

        String materialName = section.getString("material");

        if (materialName == null) {
            throw new PluginStartupException("Inserted material is invalid in 'items/" + identifier + ".yml'.");
        }

        Optional<XMaterial> xMaterialOptional = XMaterial.matchXMaterial(materialName);

        if (!xMaterialOptional.isPresent() || xMaterialOptional.get().get() == null) {
            throw new PluginStartupException("Inserted material is invalid in 'items/" + identifier + ".yml'.");
        }

        String nullableName = section.getString("name");
        String name = nullableName != null ? nullableName.replace("&", "§") : null;

        List<String> lore = section.getStringList("lore");
        lore.replaceAll(string -> string.replace("&", "§"));

        List<String> enchantmentNames = section.getStringList("enchants");
        Map<XEnchantment, Integer> enchantments = new HashMap<>();

        List<String> flagNames = section.getStringList("flags");
        Set<XItemFlag> flags = new HashSet<>();

        List<String> nbtList = section.getStringList("nbt");

        boolean stackable = section.getBoolean("stackable");

        for (String string : enchantmentNames) {

            String[] split = string.split(":");

            if (split.length != 2) {
                throw new PluginStartupException("Inserted enchantment is invalid in 'items/" + identifier + ".yml'.");
            }

            String enchantmentName = split[0];
            Optional<XEnchantment> xEnchantmentOptional = XEnchantment.of(enchantmentName);

            if (!xEnchantmentOptional.isPresent() || xEnchantmentOptional.get().get() == null) {
                throw new PluginStartupException("Inserted enchantment is invalid in 'items/" + identifier + ".yml'.");
            }

            int amount;

            try {
                amount = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new PluginStartupException("Inserted enchantment amount is invalid in 'items/" + identifier + ".yml'.");
            }

            enchantments.put(xEnchantmentOptional.get(), amount);
        }

        for (String string : flagNames) {

            Optional<XItemFlag> xItemFlagOptional = XItemFlag.of(string);

            if (!xItemFlagOptional.isPresent() || xItemFlagOptional.get().get() == null) {
                throw new PluginStartupException("Inserted item flag is invalid in 'items/" + identifier + ".yml'.");
            }

            flags.add(xItemFlagOptional.get());
        }

        return new CustomItem(identifier, xMaterialOptional.get(), name, lore, enchantments, flags, new HashSet<>(nbtList), stackable);
    }

}
