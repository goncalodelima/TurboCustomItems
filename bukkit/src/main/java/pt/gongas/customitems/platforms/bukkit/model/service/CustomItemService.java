package pt.gongas.customitems.platforms.bukkit.model.service;

import pt.gongas.customitems.platforms.bukkit.model.CustomItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CustomItemService implements CustomItemFoundationService {

    private final Map<String, CustomItem> cache = new HashMap<>();

    @Override
    public void put(CustomItem customItem) {
        cache.put(customItem.getIdentifier(), customItem);
    }

    @Override
    public Optional<CustomItem> get(String identifier) {
        return Optional.ofNullable(cache.get(identifier));
    }

    @Override
    public Set<String> getKeys() {
        return cache.keySet();
    }

}
