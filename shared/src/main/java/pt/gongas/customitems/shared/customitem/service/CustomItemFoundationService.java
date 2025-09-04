package pt.gongas.customitems.shared.customitem.service;

import pt.gongas.customitems.shared.customitem.CustomItem;

import java.util.Optional;
import java.util.Set;

public interface CustomItemFoundationService<E, F, M, I> {

    void put(CustomItem<E, F, M, I> customItem);

    Optional<CustomItem<E, F, M, I>> get(String identifier);

    Set<String> getKeys();

    void clear();

}
