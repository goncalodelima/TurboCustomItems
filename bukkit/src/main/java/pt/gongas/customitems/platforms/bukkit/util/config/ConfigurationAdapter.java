package pt.gongas.customitems.platforms.bukkit.util.config;

import org.bukkit.configuration.ConfigurationSection;

public interface ConfigurationAdapter<T> {

    T adapt(ConfigurationSection section);

}
