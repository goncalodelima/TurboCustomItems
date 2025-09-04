package pt.gongas.customitems.shared.platform;

import pt.gongas.customitems.shared.customitem.service.CustomItemFoundationService;

public interface PlatformLoader<E,F,M,I> {

    void reload();

    void registerCommands();

    void loadItems();

    CustomItemFoundationService<E,F,M,I> getCustomItemService();

    default void register() {
        registerCommands();
    }

}
