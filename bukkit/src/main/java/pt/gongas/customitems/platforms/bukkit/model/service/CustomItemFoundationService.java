package pt.gongas.customitems.platforms.bukkit.model.service;

import pt.gongas.customitems.platforms.bukkit.model.CustomItem;

import java.util.Optional;
import java.util.Set;

public interface CustomItemFoundationService {

    void put(CustomItem customItem);

    Optional<CustomItem> get(String identifier);

    Set<String> getKeys();

}
