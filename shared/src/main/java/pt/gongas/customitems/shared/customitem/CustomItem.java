package pt.gongas.customitems.shared.customitem;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public interface CustomItem<E, F, M, I> {

    String getIdentifier();

    M getMaterial();

    String getName();

    List<String> getLore();

    Map<E, Integer> getEnchantments();

    Set<F> getFlags();

    Set<String> getNbt();

    I getItem();

    boolean isStackable();

    default boolean hasSameIdentifier(CustomItem<?, ?, ?, ?> other) {
        return other != null && Objects.equals(this.getIdentifier(), other.getIdentifier());
    }

}