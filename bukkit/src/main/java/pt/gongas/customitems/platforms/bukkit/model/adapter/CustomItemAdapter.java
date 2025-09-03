package pt.gongas.customitems.platforms.bukkit.model.adapter;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import pt.gongas.customitems.platforms.bukkit.BukkitCustomItemsPlugin;
import pt.gongas.customitems.platforms.bukkit.model.CustomItem;
import pt.gongas.customitems.platforms.bukkit.util.config.ConfigurationAdapter;

import java.util.*;
import java.util.logging.Level;

public class CustomItemAdapter implements ConfigurationAdapter<CustomItem> {

    private final BukkitCustomItemsPlugin plugin;

    private String identifier;

    public CustomItemAdapter(BukkitCustomItemsPlugin plugin) {
        this.plugin = plugin;
    }

    public CustomItem adapt(String identifier, ConfigurationSection section) {
        this.identifier = identifier;
        return adapt(section);
    }

    @Override
    public CustomItem adapt(ConfigurationSection section) {

        String materialName = section.getString("material");

        if (materialName == null) {
            plugin.getLogger().log(Level.SEVERE, "Inserted material is invalid in 'items/" + identifier + ".yml'.");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return null;
        }

        Optional<XMaterial> xMaterialOptional = XMaterial.matchXMaterial(materialName);

        if (!xMaterialOptional.isPresent() || xMaterialOptional.get().get() == null) {
            plugin.getLogger().log(Level.SEVERE, "Inserted material is invalid in 'items/" + identifier + ".yml'.");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return null;
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
                plugin.getLogger().log(Level.SEVERE, "Inserted enchantment is invalid in 'items/" + identifier + ".yml'.");
                plugin.getServer().getPluginManager().disablePlugin(plugin);
                return null;
            }

            String enchantmentName = split[0];
            Optional<XEnchantment> xEnchantmentOptional = XEnchantment.of(enchantmentName);

            if (!xEnchantmentOptional.isPresent() || xEnchantmentOptional.get().get() == null) {
                plugin.getLogger().log(Level.SEVERE, "Inserted enchantment is invalid in 'items/" + identifier + ".yml'.");
                plugin.getServer().getPluginManager().disablePlugin(plugin);
                return null;
            }

            int amount;

            try {
                amount = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                plugin.getLogger().log(Level.SEVERE, "Inserted enchantment amount is invalid in 'items/" + identifier + ".yml'.");
                plugin.getServer().getPluginManager().disablePlugin(plugin);
                return null;
            }

            enchantments.put(xEnchantmentOptional.get(), amount);
        }

        for (String string : flagNames) {

            Optional<XItemFlag> xItemFlagOptional = XItemFlag.of(string);

            if (!xItemFlagOptional.isPresent() || xItemFlagOptional.get().get() == null) {
                plugin.getLogger().log(Level.SEVERE, "Inserted item flag is invalid in 'items/" + identifier + ".yml'.");
                plugin.getServer().getPluginManager().disablePlugin(plugin);
                return null;
            }

            flags.add(xItemFlagOptional.get());
        }

        ItemStack item = new ItemStack(XMaterial.STONE_BUTTON.get());

//        for (String string : nbtList) {
//
//            try {
//                NBT.parseNBT(string);
//            } catch (NbtApiException | NullPointerException e) {
//                e.printStackTrace();
//                plugin.getLogger().log(Level.SEVERE, "Inserted item nbt is invalid in 'items/" + identifier + ".yml'.");
//                plugin.getServer().getPluginManager().disablePlugin(plugin);
//                return null;
//            }
//
//        }

        return new CustomItem(identifier, xMaterialOptional.get(), name, lore, enchantments, flags, new HashSet<>(nbtList), stackable);
    }

}
