package pt.gongas.customitems.shared.customitem.adapter;

import pt.gongas.customitems.shared.util.config.ConfigurationAdapter;

public interface CustomItemAdapter<T, S> extends ConfigurationAdapter<T, S> {

    T adapt(String identifier, S section);
}