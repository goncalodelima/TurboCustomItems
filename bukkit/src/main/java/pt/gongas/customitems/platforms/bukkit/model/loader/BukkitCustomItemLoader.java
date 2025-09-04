package pt.gongas.customitems.platforms.bukkit.model.loader;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import pt.gongas.customitems.platforms.bukkit.model.adapter.BukkitCustomItemAdapter;
import pt.gongas.customitems.platforms.bukkit.util.config.BukkitConfiguration;
import pt.gongas.customitems.shared.customitem.loader.CustomItemLoader;
import pt.gongas.customitems.shared.customitem.CustomItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BukkitCustomItemLoader implements CustomItemLoader<XEnchantment, XItemFlag, XMaterial, ItemStack> {

    private final BukkitCustomItemAdapter adapter = new BukkitCustomItemAdapter();

    private final List<BukkitConfiguration> items;

    public BukkitCustomItemLoader(List<BukkitConfiguration> items) {
        this.items = items;
    }

    public Set<CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack>> setup() {

        Set<CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack>> items = new HashSet<>();

        for (BukkitConfiguration configuration : this.items) {
            ConfigurationSection section = configuration.getConfigurationSection("");
            CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack> customItem = adapter.adapt(configuration.getNameWithoutExtension(), section);
            items.add(customItem);
        }

        return items;
    }

}
