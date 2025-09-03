package pt.gongas.customitems.platforms.bukkit.model.loader;

import org.bukkit.configuration.ConfigurationSection;
import pt.gongas.customitems.platforms.bukkit.model.CustomItem;
import pt.gongas.customitems.platforms.bukkit.model.adapter.CustomItemAdapter;
import pt.gongas.customitems.platforms.bukkit.util.config.BukkitConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomItemLoader {

    private final CustomItemAdapter adapter = new CustomItemAdapter();

    private final List<BukkitConfiguration> items;

    public CustomItemLoader(List<BukkitConfiguration> items) {
        this.items = items;
    }

    public Set<CustomItem> setup() {

        Set<CustomItem> items = new HashSet<>();

        for (BukkitConfiguration configuration : this.items) {
            ConfigurationSection section = configuration.getConfigurationSection("");
            CustomItem customItem = adapter.adapt(configuration.getNameWithoutExtension(), section);
            items.add(customItem);
        }

        return items;
    }

}
