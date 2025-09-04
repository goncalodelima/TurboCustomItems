package pt.gongas.customitems.shared.customitem.loader;

import pt.gongas.customitems.shared.customitem.CustomItem;

import java.util.Set;

public interface CustomItemLoader<E, F, M, I> {

    Set<CustomItem<E, F, M, I>> setup();

}
