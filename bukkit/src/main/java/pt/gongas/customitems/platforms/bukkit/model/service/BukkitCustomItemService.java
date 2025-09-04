package pt.gongas.customitems.platforms.bukkit.model.service;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;
import pt.gongas.customitems.shared.customitem.CustomItem;
import pt.gongas.customitems.shared.customitem.service.CustomItemFoundationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BukkitCustomItemService implements CustomItemFoundationService<XEnchantment, XItemFlag, XMaterial, ItemStack> {

    private final Map<String, CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack>> cache = new HashMap<>();

    @Override
    public void put(CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack> customItem) {
        cache.put(customItem.getIdentifier(), customItem);
    }

    @Override
    public Optional<CustomItem<XEnchantment, XItemFlag, XMaterial, ItemStack>> get(String identifier) {
        return Optional.ofNullable(cache.get(identifier));
    }

    @Override
    public Set<String> getKeys() {
        return cache.keySet();
    }

    @Override
    public void clear() {
        cache.clear();
    }

}
